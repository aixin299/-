package com.axx.book.service.impl;

import com.axx.book.dao.PatientsMapper;
import com.axx.book.pojo.Patients;
import com.axx.book.service.PatientsService;
import com.axx.entity.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PatientsServiceImpl implements PatientsService {

    @Autowired
    private PatientsMapper patientsMapper;

    /**
     * Patients条件+分页查询
     * @param patients 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public List<Patients> findPage(Patients patients, int page, int size) {
        PageHelper.startPage(page, size);
        List<Patients> patientsList = patientsMapper.selectByExample(patients);
        return patientsList;
    }

    /**
     * Patients分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Patients> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Patients patients = new Patients();
        List<Patients> patientsList = patientsMapper.selectByExample(patients);
        return patientsList;
    }

    /**
     * Patients条件查询
     * @param patients
     * @return
     */
    @Override
    public List<Patients> findList(Patients patients) {
        return patientsMapper.selectByExample(patients);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        patientsMapper.deleteById(id);
    }

    /**
     * 修改Patients
     * @param patients
     */
    @Override
    public void update(Patients patients) {
        patientsMapper.updateById(patients);
    }

    /**
     * 增加Patients
     * @param patients
     */
    @Override
    public void add(Patients patients) {
        patientsMapper.insert(patients);
    }

    /**
     * 根据ID查询Patients
     * @param id
     * @return
     */
    @Override
    public Patients findById(Integer id) {
        return patientsMapper.selectById(id);
    }

    /**
     * 查询Patients全部数据
     * @return
     */
    @Override
    public List<Patients> findAll() {
        Patients patients = new Patients();
        return patientsMapper.selectByExample(patients);
    }

    @Override
    public List<Patients> findPatiens(Integer userId) {
        Patients patients = new Patients();
        patients.setUserID(userId);
        return patientsMapper.selectByExample(patients);
    }
}