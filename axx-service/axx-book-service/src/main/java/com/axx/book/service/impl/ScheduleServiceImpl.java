package com.axx.book.service.impl;

import com.axx.book.dao.ScheduleMapper;
import com.axx.book.pojo.Schedule;
import com.axx.book.service.ScheduleService;
import com.axx.entity.PageResult;
import com.axx.entity.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/****
 * @Author:axx
 * @Description:Schedule业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    /**
     * Schedule条件+分页查询
     *
     * @param schedule 查询条件
     * @param page     页码
     * @param size     页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Schedule> findPage(Schedule schedule, int page, int size) {
        PageHelper.startPage(page, size);
        List<Schedule> schedules = scheduleMapper.selectByExample(schedule);
        return new PageResult<>((long) schedules.size(), schedules);
    }

    /**
     * Schedule分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Schedule> findPage(int page, int size) {
        return findPage(null, page, size);
    }

    /**
     * Schedule条件查询
     *
     * @param schedule
     * @return
     */
    @Override
    public List<Schedule> findList(Schedule schedule) {
        return scheduleMapper.selectByExample(schedule);
    }

    /**
     * Schedule构建查询对象（这里方法名缺失，假设为 createQuery 方法）
     *
     * @param schedule
     * @return
     */
    public List<Schedule> createQuery(Schedule schedule) {
        // 这里假设只是简单调用 selectByExample 方法，可根据实际情况修改
        return scheduleMapper.selectByExample(schedule);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        scheduleMapper.deleteById(id);
    }

    /**
     * 修改Schedule
     *
     * @param schedule
     */
    @Override
    public void update(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    /**
     * 增加Schedule
     *
     * @param schedule
     */
    @Override
    public void add(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    /**
     * 根据ID查询Schedule
     *
     * @param id
     * @return
     */
    @Override
    public Schedule findById(Integer id) {
        return scheduleMapper.selectById(id);
    }

    @Override
    public List<Schedule> findAll(Long page, Long size) {
        return scheduleMapper.findAll();
    }


    /**
     * 查询当日 以后的schedule
     *
     * @param doctorId
     * @param date
     * @return
     */
    @Override
    public List<Schedule> getScheduleByDoctorId(String doctorId, String date) {
        return scheduleMapper.getScheduleByDoctorId(doctorId, date);
    }

    /**
     * 减少剩余名额
     * @param id
     */
    @Override
    public void reduceSchedule(String id) {
        scheduleMapper.reduceSchedule(id);
    }
}