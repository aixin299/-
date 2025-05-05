package com.axx.book.service;

import com.axx.book.pojo.DepartmentSecond;

import com.axx.entity.PageResult;

import java.util.List;

/****
 * @Author:axx
 * @Description:DepartmentSecond业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface DepartmentSecondService {
    /**
     * 根据名字查询科室id
     * @param departmentName
     * @return
     */
    DepartmentSecond findByName(String departmentName);
    /**
     * 根据一级科室id查询二级科室列表
     * @param id
     * @return
     */
    List<DepartmentSecond> findListById(Integer id);

    /***
     * DepartmentSecond多条件分页查询
     * @param departmentSecond
     * @param page
     * @param size
     * @return
     */
    PageResult<DepartmentSecond> findPage(DepartmentSecond departmentSecond, int page, int size);

    /***
     * DepartmentSecond分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<DepartmentSecond> findPage(int page, int size);

    /***
     * DepartmentSecond多条件搜索方法
     * @param departmentSecond
     * @return
     */
    List<DepartmentSecond> findList(DepartmentSecond departmentSecond);

    /***
     * 删除DepartmentSecond
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改DepartmentSecond数据
     * @param departmentSecond
     */
    void update(DepartmentSecond departmentSecond);

    /***
     * 新增DepartmentSecond
     * @param departmentSecond
     */
    void add(DepartmentSecond departmentSecond);

    /**
     * 根据ID查询DepartmentSecond
     * @param id
     * @return
     */
     DepartmentSecond findById(Integer id);

    /***
     * 查询所有DepartmentSecond
     * @return
     */
    List<DepartmentSecond> findAll();
}
