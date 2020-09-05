package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.dao.IOrderDao;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Order;
import com.qf.service.IOrderService;

public class OrderServiceImpl extends  BaseServiceImpl<Order> implements IOrderService {

    private IOrderDao orderDao = new OrderDaoImpl();

    @Override
    protected IBaseDao<Order> baseDao() {
        return orderDao;
    }
}
