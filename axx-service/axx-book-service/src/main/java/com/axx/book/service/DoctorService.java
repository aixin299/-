package com.axx.book.service;

import com.axx.book.pojo.Doctor;

import com.axx.entity.PageResult;

import java.util.List;

/****
 * @Author:axx
 * @Description:Doctor业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface DoctorService {

    /***
     * Doctor多条件分页查询
     * @param doctor
     * @param page
     * @param size
     * @return
     */
    PageResult<Doctor> findPage(Doctor doctor, int page, int size);

    /***
     * Doctor分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Doctor> findPage(int page, int size);

    /***
     * Doctor多条件搜索方法
     * @param doctor
     * @return
     */
    List<Doctor> findList(Doctor doctor);

    /***
     * 删除Doctor
     * @param id
     */
    int delete(Integer id);

    /***
     * 修改Doctor数据
     * @param doctor
     */
    void update(Doctor doctor);

    /***
     * 新增Doctor
     * @param doctor
     */
    void add(Doctor doctor);

    /**
     * 根据ID查询Doctor
     * @param id
     * @return
     */
     Doctor findById(Integer id);

    /***
     * 查询所有Doctor
     * @return
     */
    /**
     * 分页查询所有 Doctor
     * @param page
     * @param size
     * @return
     */
    PageResult<Doctor> findAll(int page, int size);
}
