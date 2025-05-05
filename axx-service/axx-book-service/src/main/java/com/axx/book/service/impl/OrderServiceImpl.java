package com.axx.book.service.impl;

import com.axx.book.dao.OrderMapper;
import com.axx.book.pojo.Order;
import com.axx.book.service.OrderService;
import com.axx.entity.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * Order条件+分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Order> findPage(Order order, int page, int size) {
        PageHelper.startPage(page, size);
        List<Order> orders = orderMapper.findByExample(order);
        return new PageResult<Order>((long) orders.size(), orders);
    }

    /**
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Order> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Order order = new Order();
        List<Order> orders = orderMapper.findByExample(order);
        return new PageResult<Order>((long) orders.size(), orders);
    }

    /**
     * Order条件查询
     * @param order
     * @return
     */
    @Override
    public List<Order> findList(Order order) {
        return orderMapper.findByExample(order);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        orderMapper.deleteById(id);
    }

    /**
     * 修改Order
     * @param order
     */
    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    /**
     * 增加Order
     * @param order
     */
    @Override
    public void add(Order order) {
        orderMapper.add(order);
    }

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
    @Override
    public Order findById(Integer id) {
        return orderMapper.selectById(id);
    }

    /**
     * 查询Order全部数据
     * @return
     */
    @Override
    public List<Order> findAll() {
        Order order = new Order();
        return orderMapper.findByExample(order);
    }
}