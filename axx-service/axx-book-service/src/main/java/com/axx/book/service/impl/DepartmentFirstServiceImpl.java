package com.axx.book.service.impl;

import com.axx.book.dao.DepartmentFirstMapper;
import com.axx.book.pojo.DepartmentFirst;
import com.axx.book.service.DepartmentFirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentFirstServiceImpl implements DepartmentFirstService {

    @Autowired
    private DepartmentFirstMapper departmentFirstMapper;

    @Override
    public List<DepartmentFirst> selectAll() {
        return departmentFirstMapper.selectAll();
    }

    @Override
    public List<DepartmentFirst> selectByExample(DepartmentFirst departmentFirst) {
        return departmentFirstMapper.selectByExample(departmentFirst);
    }

    @Override
    public DepartmentFirst selectById(Integer id) {
        return departmentFirstMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        departmentFirstMapper.deleteById(id);
    }

    @Override
    public void insert(DepartmentFirst departmentFirst) {
        departmentFirstMapper.insert(departmentFirst);
    }

    @Override
    public void updateById(DepartmentFirst departmentFirst) {
        departmentFirstMapper.updateById(departmentFirst);
    }
}