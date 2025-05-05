package com.axx.user.service.impl;

import com.axx.user.dao.UserMapper;
import com.axx.user.pojo.User;
import com.axx.user.service.UserService;
import com.axx.entity.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:axx
 * @Description:User业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByPhone(String phone) {
        if (phone != null) {
            return userMapper.findByPhone(phone);
        }
        return null;
    }

    /**
     * User条件+分页查询
     *
     * @param user  查询条件
     * @param page  页码
     * @param size  页大小
     * @return 分页结果
     */
    @Override
    public List<User> findPage(User user, int page, int size) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.selectByExample(user);
        return userList;
    }

    /**
     * User分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<User> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return userMapper.selectAll();
    }

    /**
     * User条件查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user) {
        return userMapper.selectByExample(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 修改User
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    /**
     * 增加User
     *
     * @param user
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询User全部数据
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * User构建查询对象
     *
     * @param user
     * @return
     */
    public String buildCondition(User user) {
        StringBuilder condition = new StringBuilder();
        if (user != null) {
            boolean hasCondition = false;
            if (!StringUtils.isEmpty(user.getId())) {
                condition.append(" AND id = '").append(user.getId()).append("'");
                hasCondition = true;
            }
            if (!StringUtils.isEmpty(user.getPhone())) {
                if (hasCondition) {
                    condition.append(" AND ");
                }
                condition.append("phone = '").append(user.getPhone()).append("'");
                hasCondition = true;
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                if (hasCondition) {
                    condition.append(" AND ");
                }
                condition.append("password = '").append(user.getPassword()).append("'");
                hasCondition = true;
            }
            // 昵称（不可用于登录）
            if (!StringUtils.isEmpty(user.getNickname())) {
                if (hasCondition) {
                    condition.append(" AND ");
                }
                condition.append("nickname LIKE '%").append(user.getNickname()).append("%'");
            }
            // 用户状态 1启用 0禁用 （用于预约黑名单）
            if (user.getStatus() != null) {
                if (hasCondition) {
                    condition.append(" AND ");
                }
                condition.append("status = ").append(user.getStatus());
            }
            // 用户角色 0普通用户1管理员
            if (user.getRole() != null) {
                if (hasCondition) {
                    condition.append(" AND ");
                }
                condition.append("role = ").append(user.getRole());
            }
        }
        // 如果有条件，去掉最前面的 " AND "
        if (condition.length() > 0 && condition.substring(0, 5).equals(" AND ")) {
            condition.delete(0, 5);
        }
        return condition.toString();
    }
}