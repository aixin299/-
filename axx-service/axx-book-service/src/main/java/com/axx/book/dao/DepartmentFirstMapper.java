package com.axx.book.dao;

import com.axx.book.pojo.DepartmentFirst;
import java.util.List;

/****
 * @Author:axx
 * @Description:DepartmentFirst的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface DepartmentFirstMapper {
    // 查询所有部门
    List<DepartmentFirst> selectAll();

    // 根据条件查询部门
    List<DepartmentFirst> selectByExample(DepartmentFirst departmentFirst);

    // 根据ID查询部门
    DepartmentFirst selectById(Integer id);

    // 根据ID删除部门
    void deleteById(Integer id);

    // 插入新的部门信息
    void insert(DepartmentFirst departmentFirst);

    // 更新部门信息
    void updateById(DepartmentFirst departmentFirst);
}