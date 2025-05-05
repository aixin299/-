package com.axx.book.dao;

import com.axx.book.pojo.Order;
import java.util.List;

/****
 * @Author:axx
 * @Description:Order的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface OrderMapper {
    void add(Order order);
    List<Order> selectAll();
    List<Order> findByExample(Order order);
    void deleteById(Integer id);
    Order selectById(Integer id);
    void updateById(Order order);

}