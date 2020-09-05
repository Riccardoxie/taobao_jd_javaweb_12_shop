package com.qf.dao.impl;

import com.qf.dao.IOrderDetailDao;
import com.qf.entity.OrderDetail;
import com.qf.utils.DBUtils;
import com.qf.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements IOrderDetailDao {
    @Override
    public int insert(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public OrderDetail selectById(Integer id) {
        return null;
    }

    @Override
    public List<OrderDetail> selectList(Integer start, Integer size) {
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

    @Override
    public void batchInsert(List<OrderDetail> orderDetails) {

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst= null;
        try {

             String sql = "insert into t_order_detail(oid,gid,gname,gprice,gpng,gdesc,count,total) values(?,?,?,?,?,?,?,?)";
            prst= connection.prepareStatement(sql);

            int i =0;
            for (OrderDetail od:orderDetails) {
                i++;
                // 给占位符赋值
                prst.setObject(1,od.getOid());
                prst.setObject(2,od.getGid());
                prst.setObject(3,od.getGname());
                prst.setObject(4,od.getGprice());
                prst.setObject(5,od.getGpng());
                prst.setObject(6,od.getGdesc());
                prst.setObject(7,od.getCount());
                prst.setObject(8,od.getTotal());

                // 添加到缓存区
                prst.addBatch();

                if(i % 100 == 0){
                    // 每100次刷新一次缓存区
                    prst.executeBatch();
                    prst.clearBatch(); // 清空缓存区
                }

            }
            prst.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(prst,connection);
        }

    }
}
