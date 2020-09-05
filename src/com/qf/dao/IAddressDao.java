package com.qf.dao;

import com.qf.entity.Address;

import java.util.List;

public interface IAddressDao extends  IBaseDao<Address>{
    List<Address> getAddressListByUid(Integer id);
}
