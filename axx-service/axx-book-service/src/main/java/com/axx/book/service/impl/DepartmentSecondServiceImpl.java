package com.axx.book.service.impl;

import com.axx.book.dao.DepartmentSecondMapper;
import com.axx.book.pojo.DepartmentSecond;
import com.axx.book.service.DepartmentSecondService;
import com.axx.entity.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DepartmentSecondServiceImpl implements DepartmentSecondService {

    @Autowired
    private DepartmentSecondMapper departmentSecondMapper;

    @Override
    public DepartmentSecond findByName(String departmentName) {
        return departmentSecondMapper.selectByName(departmentName);
    }

    /**
     * 根据一级科室id查询二级科室列表
     * @param id
     * @return
     */
    @Override
    public List<DepartmentSecond> findListById(Integer id) {
        return departmentSecondMapper.selectByDepartmentId(id);
    }

    /**
     * DepartmentSecond条件+分页查询
     * @param departmentSecond 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<DepartmentSecond> findPage(DepartmentSecond departmentSecond, int page, int size) {
        PageHelper.startPage(page, size);
        List<DepartmentSecond> list = departmentSecondMapper.selectByExample(departmentSecond);
        return new PageResult<>((long) list.size(), list);
    }

    /**
     * DepartmentSecond分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<DepartmentSecond> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        DepartmentSecond departmentSecond = new DepartmentSecond();
        List<DepartmentSecond> list = departmentSecondMapper.selectByExample(departmentSecond);
        return new PageResult<>((long) list.size(), list);
    }

    /**
     * DepartmentSecond条件查询
     * @param departmentSecond
     * @return
     */
    @Override
    public List<DepartmentSecond> findList(DepartmentSecond departmentSecond) {
        return departmentSecondMapper.selectByExample(departmentSecond);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        departmentSecondMapper.deleteById(id);
    }

    /**
     * 修改DepartmentSecond
     * @param departmentSecond
     */
    @Override
    public void update(DepartmentSecond departmentSecond) {
        departmentSecondMapper.update(departmentSecond);
    }

    /**
     * 增加DepartmentSecond
     * @param departmentSecond
     */
    @Override
    public void add(DepartmentSecond departmentSecond) {
        departmentSecondMapper.insert(departmentSecond);
    }

    /**
     * 根据ID查询DepartmentSecond
     * @param id
     * @return
     */
    @Override
    public DepartmentSecond findById(Integer id) {
        return departmentSecondMapper.selectById(id);
    }

    /**
     * 查询DepartmentSecond全部数据
     * @return
     */
    @Override
    public List<DepartmentSecond> findAll() {
        return departmentSecondMapper.selectAll();
    }
}