package com.qf.service;

import com.qf.entity.GoodsType;

import java.util.List;

public interface IGoodsTypeService extends  IBaseService<GoodsType> {

    List<GoodsType> getGoodsListByPid(Integer id);
}
