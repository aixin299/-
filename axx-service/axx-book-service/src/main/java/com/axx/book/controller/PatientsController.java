package com.axx.book.controller;

import com.axx.book.pojo.Patients;
import com.axx.book.service.PatientsService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/****
 * @Author:axx
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "PatientsController")
@RestController
@RequestMapping("/patients")
@CrossOrigin(allowCredentials = "true")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @GetMapping("/getPatients/{userid}")
    public Result getPatients(@PathVariable Integer userid, HttpServletRequest request) {

        List<Patients> patiens = patientsService.findPatiens(userid);

        return new Result(true, 200, "查询成功", patiens);
    }

    /***
     * Patients分页条件搜索实现
     * @param patients
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Patients条件分页查询", notes = "分页条件查询Patients方法详情", tags = {"PatientsController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<List<Patients>> findPage(@RequestBody(required = false) @ApiParam(name = "Patients对象", value = "传入JSON数据", required = false) Patients patients, @PathVariable int page, @PathVariable int size) {
        //调用PatientsService实现分页条件查询Patients
        List<Patients> pageResult = patientsService.findPage(patients, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /***
     * Patients分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Patients分页查询", notes = "分页查询Patients方法详情", tags = {"PatientsController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<List<Patients>> findPage(@PathVariable int page, @PathVariable int size) {
        //调用PatientsService实现分页查询Patients
        List<Patients> pageResult = patientsService.findPage(page, size);
        return new Result<List<Patients>>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param patients
     * @return
     */
    @ApiOperation(value = "Patients条件查询", notes = "条件查询Patients方法详情", tags = {"PatientsController"})
    @PostMapping(value = "/search")
    public Result<List<Patients>> findList(@RequestBody(required = false) @ApiParam(name = "Patients对象", value = "传入JSON数据", required = false) Patients patients) {
        //调用PatientsService实现条件查询Patients
        List<Patients> list = patientsService.findList(patients);
        return new Result<List<Patients>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Patients根据ID删除", notes = "根据ID删除Patients方法详情", tags = {"PatientsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用PatientsService实现根据主键删除
        patientsService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Patients数据
     * @param patients
     * @param id
     * @return
     */
    @ApiOperation(value = "Patients根据ID修改", notes = "根据ID修改Patients方法详情", tags = {"PatientsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Patients对象", value = "传入JSON数据", required = false) Patients patients, @PathVariable Integer id) {
        //设置主键值
        patients.setId(id);
        //调用PatientsService实现修改Patients
        patientsService.update(patients);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Patients数据
     * @param patients
     * @return
     */
    @ApiOperation(value = "Patients添加", notes = "添加Patients方法详情", tags = {"PatientsController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Patients对象", value = "传入JSON数据", required = true) Patients patients) {
        //调用PatientsService实现添加Patients
        patientsService.add(patients);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Patients数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Patients根据ID查询", notes = "根据ID查询Patients方法详情", tags = {"PatientsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Patients> findById(@PathVariable Integer id) {
        //调用PatientsService实现根据主键查询Patients
        Patients patients = patientsService.findById(id);
        return new Result<Patients>(true, StatusCode.OK, "查询成功", patients);
    }

    /***
     * 查询Patients全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Patients", notes = "查询所Patients有方法详情", tags = {"PatientsController"})
    @GetMapping
    public Result<List<Patients>> findAll() {
        //调用PatientsService实现查询所有Patients
        List<Patients> list = patientsService.findAll();
        return new Result<List<Patients>>(true, StatusCode.OK, "查询成功", list);
    }
}
