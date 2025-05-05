package com.axx.book.controller;

import com.axx.book.pojo.Schedule;
import com.axx.book.service.ScheduleService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/****
 * @Author:axx
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "ScheduleController")
@RestController
@RequestMapping("/schedule")
@CrossOrigin(allowCredentials = "true")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 查询医生的时间表
     * @param doctorId
     * @return
     */
    @GetMapping("/getScheduleByDoctorId/{doctorId}")
    public Result<List<Schedule>> getScheduleByDoctorId(@PathVariable String doctorId){
        try {
            LocalDate today = LocalDate.now();
            String format = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Schedule> schedules = scheduleService.getScheduleByDoctorId(doctorId, format);
            return new Result<List<Schedule>>(true, 200, "查询成功", schedules);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    /***
     * Schedule分页条件搜索实现
     * @param schedule
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Schedule条件分页查询",notes = "分页条件查询Schedule方法详情",tags = {"ScheduleController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Schedule>> findPage(@RequestBody(required = false) @ApiParam(name = "Schedule对象",value = "传入JSON数据",required = false) Schedule schedule, @PathVariable  int page, @PathVariable  int size){
        try {
            PageResult<Schedule> pageResult = scheduleService.findPage(schedule, page, size);
            return new Result(true, StatusCode.OK, "查询成功", pageResult);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    /***
     * Schedule分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Schedule分页查询",notes = "分页查询Schedule方法详情",tags = {"ScheduleController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Schedule>> findPage(@PathVariable  int page, @PathVariable  int size){
        try {
            PageResult<Schedule> pageResult = scheduleService.findPage(page, size);
            return new Result<PageResult<Schedule>>(true, StatusCode.OK, "查询成功", pageResult);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    /***
     * 多条件搜索品牌数据
     * @param schedule
     * @return
     */
    @ApiOperation(value = "Schedule条件查询",notes = "条件查询Schedule方法详情",tags = {"ScheduleController"})
    @PostMapping(value = "/search" )
    public Result<List<Schedule>> findList(@RequestBody(required = false) @ApiParam(name = "Schedule对象",value = "传入JSON数据",required = false) Schedule schedule){
        try {
            List<Schedule> list = scheduleService.findList(schedule);
            return new Result<List<Schedule>>(true, StatusCode.OK, "查询成功", list);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Schedule根据ID删除",notes = "根据ID删除Schedule方法详情",tags = {"ScheduleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        try {
            scheduleService.delete(id);
            return new Result(true, StatusCode.OK, "删除成功");
        } catch (Exception e) {
            return handleException(e, "删除失败");
        }
    }

    @DeleteMapping(value = "/deleteByIds" )
    public Result deleteByIds(@RequestParam String ids){
        try {
            String[] delids = ids.split(",");
            for (String id : delids) {
                scheduleService.delete(Integer.valueOf(id));
            }
            return new Result(true, 200, "删除成功");
        } catch (NumberFormatException e) {
            return handleException(e, "删除失败");
        } catch (Exception e) {
            return handleException(e, "删除失败");
        }
    }

    /***
     * 修改Schedule数据
     * @param schedule
     * @param id
     * @return
     */
    @ApiOperation(value = "Schedule根据ID修改",notes = "根据ID修改Schedule方法详情",tags = {"ScheduleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Schedule对象",value = "传入JSON数据",required = false) Schedule schedule,@PathVariable Integer id){
        try {
            schedule.setId(id);
            scheduleService.update(schedule);
            return new Result(true, StatusCode.OK, "修改成功");
        } catch (Exception e) {
            return handleException(e, "修改失败");
        }
    }

    /***
     * 新增Schedule数据
     * @param schedule
     * @return
     */
    @ApiOperation(value = "Schedule添加",notes = "添加Schedule方法详情",tags = {"ScheduleController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Schedule对象",value = "传入JSON数据",required = true) Schedule schedule){
        try {
            scheduleService.add(schedule);
            return new Result(true, 200, "添加成功");
        } catch (Exception e) {
            return handleException(e, "添加失败");
        }
    }

    /***
     * 根据ID查询Schedule数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Schedule根据ID查询",notes = "根据ID查询Schedule方法详情",tags = {"ScheduleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Schedule> findById(@PathVariable Integer id){
        try {
            Schedule schedule = scheduleService.findById(id);
            return new Result<Schedule>(true, StatusCode.OK, "查询成功", schedule);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    /***
     * 查询Schedule全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Schedule（分页）",notes = "查询所Schedule有方法详情",tags = {"ScheduleController"})
    @GetMapping
    public Result<PageResult<Schedule>> findAll(@RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") Long size){
        try {
            List<Schedule> list = scheduleService.findAll(page, size);
            PageResult<Schedule> objectPageResult = new PageResult<>((long)list.size(),list);

            return new Result<PageResult<Schedule>>(true, StatusCode.OK, "查询成功", objectPageResult);
        } catch (Exception e) {
            return handleException(e, "查询失败");
        }
    }

    private Result handleException(Exception e, String errorMessage) {
        e.printStackTrace();
        return new Result(false, 300, errorMessage);
    }
}