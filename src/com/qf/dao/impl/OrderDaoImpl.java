package com.qf.dao.impl;

import com.qf.dao.IOrderDao;
import com.qf.entity.Order;
import com.qf.utils.DaoUtils;

import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    @Override
    public int insert(Order order) {
        String sql = "insert into t_order(uid,status,totalPrice,payType,sendType,name,phone,address,createDate) values(?,?,?,?,?,?,?,?,now())";
        return DaoUtils.commonInsert(sql,order.getUid(),order.getStatus(),order.getTotalPrice(),order.getPayType(),order.getSendType(),order.getName(),order.getPhone(),order.getAddress());
    }

    @Override
    public int update(Order order) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Order selectById(Integer id) {
        return null;
    }

    @Override
    public List<Order> selectList(Integer start, Integer size) {
        return null;
    }

    @Override
    public int selectCount() {
        return 0;
    }

    @Override
    public <T> List<T> getAll() {
        return null;
    }
}
