package com.qf.dao;

import com.qf.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailDao extends  IBaseDao<OrderDetail>{
    void batchInsert(List<OrderDetail> orderDetails);
}
