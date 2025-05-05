package com.axx.user.dao;

import com.axx.user.pojo.User;

import java.util.List;

/****
 * @Author:axx
 * @Description:User的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface UserMapper {

    // 查询所有用户
    List<User> selectAll();

    // 根据条件查询用户
    List<User> selectByExample(User user);

    // 根据手机号查询用户
    User findByPhone(String phone);

    // 根据ID查询单个用户
    User selectById(Integer id);

    // 插入新用户
    void insert(User user);

    // 更新用户信息
    void updateById(User user);

    // 根据ID删除用户
    void deleteById(Integer id);
}