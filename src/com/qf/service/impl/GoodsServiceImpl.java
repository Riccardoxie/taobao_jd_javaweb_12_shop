package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.dao.IGoodsDao;
import com.qf.dao.impl.GoodsDaoImpl;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;

public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService {

    private IGoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    protected IBaseDao<Goods> baseDao() {
        return goodsDao;
    }
}
