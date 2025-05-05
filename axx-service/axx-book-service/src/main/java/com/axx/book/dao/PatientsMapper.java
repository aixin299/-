package com.axx.book.dao;

import com.axx.book.pojo.Patients;
import java.util.List;

/****
 * @Author:axx
 * @Description:Patients的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface PatientsMapper {
    // 添加患者
    void insert(Patients patients);
    // 查询所有患者
    List<Patients> selectAll();
    // 根据条件查询患者
    List<Patients> selectByExample(Patients patients);
    // 根据ID删除患者
    void deleteById(Integer id);
    // 根据ID查询患者
    Patients selectById(Integer id);
    // 根据ID更新患者信息
    void updateById(Patients patients);
}