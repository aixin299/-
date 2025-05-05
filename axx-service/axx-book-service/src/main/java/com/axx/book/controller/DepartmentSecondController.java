package com.axx.book.controller;
import com.axx.book.pojo.DepartmentSecond;
import com.axx.book.service.DepartmentSecondService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:axx
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "DepartmentSecondController")
@RestController
@RequestMapping("/departmentSecond")
@CrossOrigin(allowCredentials = "true")
public class DepartmentSecondController {

    @Autowired
    private DepartmentSecondService departmentSecondService;
    @GetMapping("/findByName/{name}")
    public Result findByName(@PathVariable String name){
        DepartmentSecond department = departmentSecondService.findByName(name);
        return new Result(true,200,"查询成功",department);
    }
    /***
     * DepartmentSecond分页条件搜索实现
     * @param departmentSecond
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "DepartmentSecond条件分页查询",notes = "分页条件查询DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<DepartmentSecond>> findPage(@RequestBody(required = false) @ApiParam(name = "DepartmentSecond对象",value = "传入JSON数据",required = false) DepartmentSecond departmentSecond, @PathVariable  int page, @PathVariable  int size){
        //调用DepartmentSecondService实现分页条件查询DepartmentSecond
        PageResult<DepartmentSecond> pageResult = departmentSecondService.findPage(departmentSecond, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * DepartmentSecond分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "DepartmentSecond分页查询",notes = "分页查询DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<DepartmentSecond>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用DepartmentSecondService实现分页查询DepartmentSecond
        PageResult<DepartmentSecond> pageResult = departmentSecondService.findPage(page, size);
        return new Result<PageResult<DepartmentSecond>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param departmentSecond
     * @return
     */
    @ApiOperation(value = "DepartmentSecond条件查询",notes = "条件查询DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @PostMapping(value = "/search" )
    public Result<List<DepartmentSecond>> findList(@RequestBody(required = false) @ApiParam(name = "DepartmentSecond对象",value = "传入JSON数据",required = false) DepartmentSecond departmentSecond){
        //调用DepartmentSecondService实现条件查询DepartmentSecond
        List<DepartmentSecond> list = departmentSecondService.findList(departmentSecond);
        return new Result<List<DepartmentSecond>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentSecond根据ID删除",notes = "根据ID删除DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用DepartmentSecondService实现根据主键删除
        departmentSecondService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改DepartmentSecond数据
     * @param departmentSecond
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentSecond根据ID修改",notes = "根据ID修改DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "DepartmentSecond对象",value = "传入JSON数据",required = false) DepartmentSecond departmentSecond,@PathVariable Integer id){
        //设置主键值
        departmentSecond.setId(id);
        //调用DepartmentSecondService实现修改DepartmentSecond
        departmentSecondService.update(departmentSecond);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增DepartmentSecond数据
     * @param departmentSecond
     * @return
     */
    @ApiOperation(value = "DepartmentSecond添加",notes = "添加DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "DepartmentSecond对象",value = "传入JSON数据",required = true) DepartmentSecond departmentSecond){
        //调用DepartmentSecondService实现添加DepartmentSecond
        departmentSecondService.add(departmentSecond);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询DepartmentSecond数据
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentSecond根据ID查询",notes = "根据ID查询DepartmentSecond方法详情",tags = {"DepartmentSecondController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<DepartmentSecond> findById(@PathVariable Integer id){
        //调用DepartmentSecondService实现根据主键查询DepartmentSecond
        DepartmentSecond departmentSecond = departmentSecondService.findById(id);
        return new Result<DepartmentSecond>(true,StatusCode.OK,"查询成功",departmentSecond);
    }

    /***
     * 查询DepartmentSecond全部数据
     * @return
     */
    @ApiOperation(value = "查询所有DepartmentSecond（分页）",notes = "查询所DepartmentSecond有方法详情",tags = {"DepartmentSecondController"})
    @GetMapping
    public Result<PageResult<DepartmentSecond>> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        //调用DepartmentSecondService实现查询所有DepartmentSecond（分页）
        PageHelper.startPage(page,size);
        List<DepartmentSecond> list = departmentSecondService.findAll();

        return new Result<PageResult<DepartmentSecond>>(true, StatusCode.OK,"查询成功",
                new PageResult<>((long) list.size(), list)) ;
    }
}
