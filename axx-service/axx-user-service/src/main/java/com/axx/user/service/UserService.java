package com.axx.user.service;

import com.axx.user.pojo.User;

import com.axx.entity.PageResult;

import java.util.List;

/****
 * @Author:axx
 * @Description:User业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface UserService {
    User findByPhone(String phone);

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    List<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    List<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改User数据
     * @param user
     */
    void update(User user);

    /***
     * 新增User
     * @param user
     */
    void add(User user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
     User findById(Integer id);

    /***
     * 查询所有User
     * @return
     */
    List<User> findAll();
}
