package com.axx.book.service;

import com.axx.book.pojo.Schedule;

import com.axx.entity.PageResult;

import java.util.List;

/****
 * @Author:axx
 * @Description:Schedule业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface ScheduleService {

    /***
     * Schedule多条件分页查询
     * @param schedule
     * @param page
     * @param size
     * @return
     */
    PageResult<Schedule> findPage(Schedule schedule, int page, int size);

    /***
     * Schedule分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Schedule> findPage(int page, int size);

    /***
     * Schedule多条件搜索方法
     * @param schedule
     * @return
     */
    List<Schedule> findList(Schedule schedule);

    /***
     * 删除Schedule
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Schedule数据
     * @param schedule
     */
    void update(Schedule schedule);

    /***
     * 新增Schedule
     * @param schedule
     */
    void add(Schedule schedule);

    /**
     * 根据ID查询Schedule
     * @param id
     * @return
     */
     Schedule findById(Integer id);

    /***
     * 查询所有Schedule
     * @return
     */
    List<Schedule> findAll(Long page,Long size);

    List<Schedule> getScheduleByDoctorId(String doctorId,String date);

    void reduceSchedule(String id);
}
