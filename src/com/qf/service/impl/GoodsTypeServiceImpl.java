package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.dao.IGoodsTypeDao;
import com.qf.dao.impl.GoodsTypeDaoImpl;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;

import java.util.List;

public class GoodsTypeServiceImpl  extends  BaseServiceImpl<GoodsType> implements IGoodsTypeService {

    private IGoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();

    @Override
    protected IBaseDao<GoodsType> baseDao() {
        return goodsTypeDao;
    }

    @Override
    public List<GoodsType> getGoodsListByPid(Integer id) {
        return goodsTypeDao.getGoodsListByPid(id);
    }
}
