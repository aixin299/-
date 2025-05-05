/**
 * 此包包含患者服务相关的接口和实现类。
 */
package com.axx.book.service;

import com.axx.book.pojo.Patients;

import com.axx.entity.PageResult;
import io.swagger.models.auth.In;

import java.util.List;

/****
 * @Author:axx
 * @Description:Patients业务层接口
 * @Date 2021/2/1 14:19
 *****/

/**
 * Patients 业务层接口，定义了患者信息管理的相关业务方法。
 *
 * @Author: axx
 * @Date 2021/2/1 14:19
 */
public interface PatientsService {

    /***
     * Patients多条件分页查询
     * @param patients
     * @param page
     * @param size
     * @return
     */
    List<Patients> findPage(Patients patients, int page, int size);

    /***
     * Patients分页查询
     * @param page
     * @param size
     * @return
     */
    List<Patients> findPage(int page, int size);

    /***
     * Patients多条件搜索方法
     * @param patients
     * @return
     */
    List<Patients> findList(Patients patients);

    /***
     * 删除Patients
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Patients数据
     * @param patients
     */
    void update(Patients patients);

    /***
     * 新增Patients
     * @param patients
     */
    void add(Patients patients);

    /**
     * 根据ID查询Patients
     * @param id
     * @return
     */
     Patients findById(Integer id);

    /***
     * 查询所有Patients
     * @return
     */
    List<Patients> findAll();

    List<Patients> findPatiens(Integer userId);
}
