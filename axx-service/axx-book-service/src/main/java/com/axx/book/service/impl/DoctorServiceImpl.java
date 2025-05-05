package com.axx.book.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.axx.book.dao.DoctorMapper;
import com.axx.book.pojo.Doctor;
import com.axx.book.service.DoctorService;
import com.axx.entity.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * Doctor条件+分页查询
     * @param doctor 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Doctor> findPage(Doctor doctor, int page, int size) {
        PageHelper.startPage(page, size);
        List<Doctor> doctors = doctorMapper.selectByExample(doctor);
        return new PageResult<Doctor>((long) doctors.size(), doctors);
    }
    
    /**
     * Doctor分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Doctor> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Doctor doctor = new Doctor(); // 这里创建一个空的Doctor对象，因为不需要条件查询
        List<Doctor> doctors = doctorMapper.selectByExample(doctor);
        return new PageResult<Doctor>((long) doctors.size(), doctors);
    }

    /**
     * Doctor条件查询
     * @param doctor
     * @return
     */
    @Override
    public List<Doctor> findList(Doctor doctor) {
        return doctorMapper.selectByExample(doctor);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Integer id) {
        return doctorMapper.deleteById(id);
    }

    /**
     * 修改Doctor
     * @param doctor
     */
    @Override
    public void update(Doctor doctor) {
        doctorMapper.update(doctor);
    }

    /**
     * 增加Doctor
     * @param doctor
     */
    @Override
    public void add(Doctor doctor) {
        doctorMapper.insert(doctor);
    }

    /**
     * 根据ID查询Doctor
     * @param id
     * @return
     */
    @Override
    public Doctor findById(Integer id) {
        return doctorMapper.selectById(id);
    }

    /**
     * 查询Doctor全部数据（分页）
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Doctor> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Doctor> doctors = doctorMapper.selectAll();
        return new PageResult<Doctor>((long) doctors.size(), doctors);
    }
}