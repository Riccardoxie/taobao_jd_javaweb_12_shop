package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.dao.IOrderDetailDao;
import com.qf.dao.impl.OrderDetailDaoImpl;
import com.qf.entity.OrderDetail;
import com.qf.service.IOrderDetailService;
import com.qf.service.IOrderService;

import java.util.List;

public class OrderDetailServiceImpl extends  BaseServiceImpl<OrderDetail> implements IOrderDetailService {

    private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    protected IBaseDao<OrderDetail> baseDao() {
        return orderDetailDao;
    }

    @Override
    public void batchInsert(List<OrderDetail> orderDetails) {
        orderDetailDao.batchInsert(orderDetails);
    }
}
