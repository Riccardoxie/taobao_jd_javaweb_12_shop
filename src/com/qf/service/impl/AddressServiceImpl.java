package com.qf.service.impl;

import com.qf.dao.IAddressDao;
import com.qf.dao.IBaseDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.entity.Address;
import com.qf.service.IAddressService;

import java.util.List;

public class AddressServiceImpl extends  BaseServiceImpl<Address> implements IAddressService {

    private IAddressDao addressDao = new AddressDaoImpl();

    @Override
    protected IBaseDao<Address> baseDao() {
        return addressDao;
    }

    @Override
    public List<Address> getAddressListByUid(Integer id) {
        return addressDao.getAddressListByUid(id);
    }
}
