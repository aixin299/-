package com.axx.user.controller;

import com.axx.user.pojo.User;
import com.axx.user.service.UserService;
import com.axx.utils.RandomValidateCodeUtil;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import com.axx.utils.PhoneFormatCheckUtils;
import io.swagger.annotations.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:axx
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ckeckLogin")
    public Result checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute("phone");
        String nickname = (String) session.getAttribute("nickname");
        Integer id = (Integer) session.getAttribute("id");

        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("nickname",nickname);
        map.put("userid",id);

        if (Strings.isEmpty(phone) || Strings.isEmpty(nickname) ){
            return new Result(false,300,"未登录");
        }else {
            return new Result(false,200,"用户已登录",map);
        }

    }

    @GetMapping("/getImg")
    public void getBaseCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = RandomValidateCodeUtil.createMumAndCharTwo();
        //将验证码存入Session
        System.out.println("验证码" + objs[0]);
        session.setAttribute("imageCode",objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);

    }

    @PostMapping("/register")
    public Result register(@RequestBody Map map){
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        String password = (String) map.get("password");
        String nickname = (String) map.get("nickname");
        //先查看是否已经注册
        User byPhone = userService.findByPhone(phone);
        if (byPhone != null){
            return new Result(false, 301, "该手机号已注册", null);
        }
        //得到缓存中存储的验证码
        String sysCode = (String) redisTemplate.boundHashOps("smscode").get(phone);
        if (sysCode == null && !sysCode.equals(code)) {
            System.out.println("验证码错误");
            return new Result(true, 300, "验证码错误", null);
        }
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setRole(0);
        user.setStatus(1);
        userService.update(user);
        return new Result(true,200,"注册成功，即将为你跳转到登录页面",null);
    }

    @PostMapping("/checkImgCode")
    public Result checkImgCode(@RequestBody Map map,HttpServletRequest request){
        HttpSession session = request.getSession();
        String imageCode = (String) session.getAttribute("imageCode");
        String code = (String) map.get("code");
        System.out.println(code);
        System.out.println(imageCode);
        if (code.equalsIgnoreCase(imageCode)){
            return new Result(true,200,"验证码正确");
        }else {
            return new Result(false,300,"验证码错误");
        }
    }
    /**
     * 密码登录
     * @param loginuser
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User loginuser,HttpServletRequest request) {
        String phone = loginuser.getPhone();
        String password = loginuser.getPassword();
        System.out.println(phone + " " + password);
        User user = userService.findByPhone(phone);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("登陆成功");
            String nickname = user.getNickname();
            // 将phone和昵称存入session
            HttpSession session = request.getSession();
            session.setAttribute("phone",phone);
            session.setAttribute("nickname",nickname);
            session.setAttribute("id",user.getId());

            return new Result(true, 200, "登录成功", null);
        } else {
            System.out.println("登陆失败");
            return new Result(false, 300, "账号或密码错误", null);
        }
    }

    /**
     * 验证码登录
     * @param map
     * @return
     */
    @PostMapping("/loginByCode")
    public Result loginByCode(@RequestBody Map map,HttpServletRequest request) {

        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        System.out.println(phone + " " + code);
        //得到缓存中存储的验证码
        String sysCode = (String) redisTemplate.boundHashOps("smscode").get(phone);

        if (sysCode != null && sysCode.equals(code)) {
            User user = userService.findByPhone(phone);
            String nickname = user.getNickname();
            // 将phone和昵称存入session
            HttpSession session = request.getSession();
            session.setAttribute("phone",phone);
            session.setAttribute("nickname",nickname);

            System.out.println("登陆成功");
            return new Result(true, 200, "登录成功", null);
        } else {
            System.out.println("登陆失败");
            return new Result(false, 300, "手机号或验证码错误", null);
        }
    }

    @GetMapping("/getCode")
    public Result getCode(String phone) {
        try {
            //检验手机号是否正确
            if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
                return new Result(false,StatusCode.ERROR,"手机号格式不正确");
            }

            //生成6位随机数
            String code = (long) (Math.random() * 1000000) + "";
            System.out.println("验证码：" + code);
            //将验证码存入Redis缓存
            redisTemplate.boundHashOps("smscode").put(phone, code);
            //发送到RabbitMQ ....
            Map map = new HashMap();
            map.put("mobile",phone);
            map.put("code",code);
            rabbitTemplate.convertAndSend("axx.sms.queue",map);
        }catch (Exception e){
            return new Result(true, StatusCode.ACCESSERROR, "验证码发送失败，请稍后再试", "");
        }


        return new Result(true, StatusCode.OK, "短信验证码发送成功", "");
    }


    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "User条件分页查询", notes = "分页条件查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<List<User>> findPage(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        List<User> pageResult = userService.findPage(user, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "User分页查询", notes = "分页查询User方法详情", tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<List<User>> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        List<User> pageResult = userService.findPage(page, size);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User条件查询", notes = "条件查询User方法详情", tags = {"UserController"})
    @PostMapping(value = "/search")
    public Result<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user) {
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID删除", notes = "根据ID删除User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID修改", notes = "根据ID修改User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = false) User user, @PathVariable Integer id) {
        //设置主键值
        user.setId(id);
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User添加", notes = "添加User方法详情", tags = {"UserController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "User对象", value = "传入JSON数据", required = true) User user) {
        //调用UserService实现添加User
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询", notes = "根据ID查询User方法详情", tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Integer id) {
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @ApiOperation(value = "查询所有User（分页）", notes = "查询所User有方法详情", tags = {"UserController"})
    @GetMapping
    public Result<List<User>> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        //调用UserService实现查询所有User（分页）
        List<User> pageResult = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", pageResult);
    }
}
