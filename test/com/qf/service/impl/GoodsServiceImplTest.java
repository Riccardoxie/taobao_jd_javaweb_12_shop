package com.qf.service.impl;

import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.service.IGoodsService;
import org.junit.Test;
//import org.junit.Test;

public class GoodsServiceImplTest {

    private IGoodsService goodsService = new GoodsServiceImpl();

    @Test
    public void insert() throws Exception {
        for(int i =0;i<10;i++){
            Goods goods = new Goods();
            goods.setGname("TCL电视_"+i);
            goods.setGdesc("高清 4k 曲面屏");
            goods.setGnum(10);
            goods.setGpid(4);
            goods.setGfid(7);
            goods.setGpng("xxx.png");
            goods.setGprice(4999.0);
            goods.setGpriceOff(3444.4);

            int insert = goodsService.insert(goods);
            System.out.println(insert);
        }
    }

    @Test
    public void selectPage() throws Exception {
        Page<Goods> page = new Page<>();
        goodsService.selectPage(page);
        System.out.println(page);
    }


}
