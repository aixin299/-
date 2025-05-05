package com.axx.book.service;

import com.axx.book.pojo.DepartmentFirst;
import java.util.List;

public interface DepartmentFirstService {
    List<DepartmentFirst> selectAll();
    List<DepartmentFirst> selectByExample(DepartmentFirst departmentFirst);
    DepartmentFirst selectById(Integer id);
    void deleteById(Integer id);
    void insert(DepartmentFirst departmentFirst);
    void updateById(DepartmentFirst departmentFirst);
}