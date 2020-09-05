package com.qf.service;

import com.qf.entity.Address;

import java.util.List;

public interface IAddressService extends  IBaseService<Address> {
    List<Address> getAddressListByUid(Integer id);
}
