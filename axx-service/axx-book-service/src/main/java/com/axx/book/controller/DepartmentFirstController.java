package com.axx.book.controller;

import com.axx.book.pojo.DepartmentFirst;
import com.axx.book.service.DepartmentFirstService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.axx.entity.StatusCode;
import io.swagger.annotations.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "DepartmentFirstController")
@RestController
@RequestMapping("/departmentFirst")
@CrossOrigin(allowCredentials = "true")
public class DepartmentFirstController {

    @Autowired
    private DepartmentFirstService departmentFirstService;

    @GetMapping("/getDepartments")
    public Result getDepartments() {
        try {
            List<DepartmentFirst> all = departmentFirstService.selectAll();
            return new Result(true, 200, "查询成功", all);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }

    /***
     * DepartmentFirst分页条件搜索实现
     * @param departmentFirst
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "DepartmentFirst条件分页查询", notes = "分页条件查询DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageResult<DepartmentFirst>> findPage(@RequestBody(required = false) @ApiParam(name = "DepartmentFirst对象", value = "传入JSON数据", required = false) DepartmentFirst departmentFirst, @PathVariable int page, @PathVariable int size) {
        try {
            PageHelper.startPage(page, size);
            List<DepartmentFirst> list = departmentFirstService.selectByExample(departmentFirst);
            PageInfo<DepartmentFirst> pageInfo = new PageInfo<>(list);
            PageResult<DepartmentFirst> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
            return new Result(true, StatusCode.OK, "查询成功", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }

    /***
     * DepartmentFirst分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "DepartmentFirst分页查询", notes = "分页查询DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<DepartmentFirst>> findPage(@PathVariable int page, @PathVariable int size) {
        try {
            PageHelper.startPage(page, size);
            List<DepartmentFirst> list = departmentFirstService.selectAll();
            PageInfo<DepartmentFirst> pageInfo = new PageInfo<>(list);
            PageResult<DepartmentFirst> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
            return new Result(true, StatusCode.OK, "查询成功", pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }

    /***
     * 多条件搜索品牌数据
     * @param departmentFirst
     * @return
     */
    @ApiOperation(value = "DepartmentFirst条件查询", notes = "条件查询DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @PostMapping(value = "/search")
    public Result<List<DepartmentFirst>> findList(@RequestBody(required = false) @ApiParam(name = "DepartmentFirst对象", value = "传入JSON数据", required = false) DepartmentFirst departmentFirst) {
        try {
            List<DepartmentFirst> list = departmentFirstService.selectByExample(departmentFirst);
            return new Result<List<DepartmentFirst>>(true, StatusCode.OK, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentFirst根据ID删除", notes = "根据ID删除DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            departmentFirstService.deleteById(id);
            return new Result(true, StatusCode.OK, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "删除失败");
        }
    }

    /***
     * 修改DepartmentFirst数据
     * @param departmentFirst
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentFirst根据ID修改", notes = "根据ID修改DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "DepartmentFirst对象", value = "传入JSON数据", required = false) DepartmentFirst departmentFirst, @PathVariable Integer id) {
        try {
            departmentFirst.setId(id);
            departmentFirstService.updateById(departmentFirst);
            return new Result(true, StatusCode.OK, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "修改失败");
        }
    }

    /***
     * 新增DepartmentFirst数据
     * @param departmentFirst
     * @return
     */
    @ApiOperation(value = "DepartmentFirst添加", notes = "添加DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "DepartmentFirst对象", value = "传入JSON数据", required = true) DepartmentFirst departmentFirst) {
        try {
            departmentFirstService.insert(departmentFirst);
            return new Result(true, StatusCode.OK, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "添加失败");
        }
    }

    /***
     * 根据ID查询DepartmentFirst数据
     * @param id
     * @return
     */
    @ApiOperation(value = "DepartmentFirst根据ID查询", notes = "根据ID查询DepartmentFirst方法详情", tags = {"DepartmentFirstController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<DepartmentFirst> findById(@PathVariable Integer id) {
        try {
            DepartmentFirst departmentFirst = departmentFirstService.selectById(id);
            return new Result<DepartmentFirst>(true, StatusCode.OK, "查询成功", departmentFirst);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }

    /***
     * 查询DepartmentFirst全部数据
     * @return
     */
    @ApiOperation(value = "查询所有DepartmentFirst", notes = "查询所DepartmentFirst有方法详情", tags = {"DepartmentFirstController"})
    @GetMapping
    public Result<List<DepartmentFirst>> findAll() {
        try {
            List<DepartmentFirst> list = departmentFirstService.selectAll();
            return new Result<List<DepartmentFirst>>(true, StatusCode.OK, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
    }
}