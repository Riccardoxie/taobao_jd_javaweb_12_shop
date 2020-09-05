package com.qf.dao.impl;

import com.qf.dao.IAddressDao;
import com.qf.entity.Address;
import com.qf.utils.DaoUtils;

import java.util.List;

public class AddressDaoImpl implements IAddressDao {
    @Override
    public int insert(Address address) {
        return 0;
    }

    @Override
    public int update(Address address) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Address selectById(Integer id) {
        return null;
    }

    @Override
    public List<Address> selectList(Integer start, Integer size) {
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
    public List<Address> getAddressListByUid(Integer id) {
        String sql = "select * from t_address where uid = ?";
        return DaoUtils.commonQuery(sql,Address.class,id);
    }
}
