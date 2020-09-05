package com.qf.dao;

import com.qf.entity.GoodsType;

import java.util.List;

public interface IGoodsTypeDao extends  IBaseDao<GoodsType> {
    List<GoodsType> getGoodsListByPid(Integer id);
}
