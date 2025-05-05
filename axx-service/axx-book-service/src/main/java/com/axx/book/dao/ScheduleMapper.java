package com.axx.book.dao;

import com.axx.book.pojo.Schedule;

import java.util.List;

/****
 * @Author:axx
 * @Description:Schedule的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface ScheduleMapper {

    List<Schedule> getScheduleByDoctorId(String doctorId,String date);

    int reduceSchedule(String id);

    List<Schedule> findAll();

    List<Schedule> selectByExample(Schedule schedule);

    List<Schedule> update(Schedule schedule);

    // 根据ID查询单个Schedule信息
    Schedule selectById( Integer id);

    Schedule deleteById( Integer id);


    // 插入新的Schedule信息
    void insert(Schedule schedule);
}