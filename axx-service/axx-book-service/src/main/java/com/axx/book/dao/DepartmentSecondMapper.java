package com.axx.book.dao;

import com.axx.book.pojo.DepartmentSecond;
import java.util.List;

/****
 * @Author:axx
 * @Description:DepartmentSecond的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface DepartmentSecondMapper {
    /**
     * 根据名称查询部门
     * @param name 部门名称
     * @return 部门信息
     */
    DepartmentSecond selectByName(String name);

    /**
     * 查询所有部门
     * @return 部门列表
     */
    List<DepartmentSecond> selectAll();

    /**
     * 根据条件查询部门
     * @param departmentSecond 查询条件
     * @return 部门列表
     */
    List<DepartmentSecond> selectByExample(DepartmentSecond departmentSecond);

    /**
     * 根据部门ID查询部门
     * @param id 部门ID
     * @return 部门信息
     */
    DepartmentSecond selectById(Integer id);

    /**
     * 根据部门ID删除部门
     * @param id 部门ID
     */
    void deleteById(Integer id);

    /**
     * 插入新的部门信息
     * @param departmentSecond 部门信息
     */
    void insert(DepartmentSecond departmentSecond);

    /**
     * 更新部门信息
     * @param departmentSecond 部门信息
     */
    void update(DepartmentSecond departmentSecond);

    /**
     * 根据一级部门ID查询二级部门
     * @param departmentId 一级部门ID
     * @return 二级部门列表
     */
    List<DepartmentSecond> selectByDepartmentId(Integer departmentId);
}