package com.axx.book.controller;

import com.axx.book.pojo.Doctor;
import com.axx.book.service.DoctorService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:axx
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "DoctorController")
@RestController
@RequestMapping("/doctor")
@CrossOrigin(allowCredentials = "true")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /***
     * Doctor分页条件搜索实现
     * @param doctor
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Doctor条件分页查询",notes = "分页条件查询Doctor方法详情",tags = {"DoctorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Doctor>> findPage(@RequestBody(required = false) @ApiParam(name = "Doctor对象",value = "传入JSON数据",required = false) Doctor doctor, @PathVariable  int page, @PathVariable  int size){
        //调用DoctorService实现分页条件查询Doctor
        PageResult<Doctor> pageResult = doctorService.findPage(doctor, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * Doctor分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Doctor分页查询",notes = "分页查询Doctor方法详情",tags = {"DoctorController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Doctor>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用DoctorService实现分页查询Doctor
        PageResult<Doctor> pageResult = doctorService.findPage(page, size);
        return new Result<PageResult<Doctor>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param doctor
     * @return
     */
    @ApiOperation(value = "Doctor条件查询",notes = "条件查询Doctor方法详情",tags = {"DoctorController"})
    @PostMapping(value = "/search" )
    public Result<List<Doctor>> findList(@RequestBody(required = false) @ApiParam(name = "Doctor对象",value = "传入JSON数据",required = false) Doctor doctor){
        //调用DoctorService实现条件查询Doctor
        List<Doctor> list = doctorService.findList(doctor);
        return new Result<List<Doctor>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Doctor根据ID删除",notes = "根据ID删除Doctor方法详情",tags = {"DoctorController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用DoctorService实现根据主键删除
        doctorService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @DeleteMapping(value = "/deleteByIds" )
    public Result deleteByIds(@RequestParam String ids){
        try {
            String[] delids = ids.split(",");
            for (String id : delids) {
                doctorService.delete(Integer.valueOf(id));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(false,300,"删除失败");
        }
        return new Result(true,200,"删除成功");
    }

    /**
     * 修改doctor
     * @param doctor
     * @return
     */
    @ApiOperation(value = "Doctor根据ID修改",notes = "根据ID修改Doctor方法详情",tags = {"DoctorController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PostMapping(value="/update")
    public Result update(@RequestBody @ApiParam(name = "Doctor对象",value = "传入JSON数据",required = false) Doctor doctor){
        //设置主键值

        //调用DoctorService实现修改Doctor
        System.out.println(doctor);
        doctorService.update(doctor);
        return new Result(true,200,"修改成功");
    }

    /***
     * 新增Doctor数据
     * @param doctor
     * @return
     */
    @ApiOperation(value = "Doctor添加",notes = "添加Doctor方法详情",tags = {"DoctorController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Doctor对象",value = "传入JSON数据",required = true) Doctor doctor){
        //调用DoctorService实现添加Doctor
        try {
            doctorService.add(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,300,"添加失败");
        }
        return new Result(true,200,"添加成功");
    }

    /***
     * 根据ID查询Doctor数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Doctor根据ID查询",notes = "根据ID查询Doctor方法详情",tags = {"DoctorController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Doctor> findById(@PathVariable Integer id){
        //调用DoctorService实现根据主键查询Doctor
        Doctor doctor = doctorService.findById(id);
        return new Result<Doctor>(true,StatusCode.OK,"查询成功",doctor);
    }
    @GetMapping("/findByDeptId/{deptId}")
    public Result<List<Doctor>> findByDeptId(@PathVariable Integer deptId){
        //调用DoctorService实现根据部门查询Doctor
        List<Doctor> doctors = null;
        try {
            Doctor doctor = new Doctor();
            doctor.setDepartmentId(deptId);
            doctors = doctorService.findList(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<List<Doctor>>(false,300,"查询失败");
        }
        return new Result<List<Doctor>>(true,200,"查询成功",doctors);
    }

    /***
     * 查询Doctor全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Doctor",notes = "查询所Doctor有方法详情",tags = {"DoctorController"})
    @GetMapping
    public Result<List<Doctor>> findAll(int page, int size){
        //调用DoctorService实现查询所有Doctor
        PageResult<Doctor> list = doctorService.findAll(page, size);
        return new Result<List<Doctor>>(true, StatusCode.OK,"查询成功",list.getRows()) ;
    }
}
