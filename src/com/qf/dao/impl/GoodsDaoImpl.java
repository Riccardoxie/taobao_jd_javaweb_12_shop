package com.qf.dao.impl;

import com.qf.dao.IGoodsDao;
import com.qf.entity.Goods;
import com.qf.utils.DaoUtils;

import java.util.List;

public class GoodsDaoImpl implements IGoodsDao {

    @Override
    public int insert(Goods goods) {
        String sql ="insert into t_goods(gname,gdesc,gprice,gpriceOff,gnum,gpng,gpid,gfid,fileName) values(?,?,?,?,?,?,?,?,?)";
        return DaoUtils.commonUpdate(sql,goods.getGname(),goods.getGdesc(),goods.getGprice(),goods.getGpriceOff(),goods.getGnum(),goods.getGpng(),goods.getGpid(),goods.getGfid(),goods.getFileName());

    }

    @Override
    public int update(Goods goods) {
        String sql ="update t_goods set gname = ?,gdesc = ?,gprice = ?,gpriceOff = ?,gnum = ?,gpng = ?,gpid = ?,gfid = ?,fileName = ? where id = ?";
        return DaoUtils.commonUpdate(sql,goods.getGname(),goods.getGdesc(),goods.getGprice(),goods.getGpriceOff(),goods.getGnum(),goods.getGpng(),goods.getGpid(),goods.getGfid(),goods.getFileName(),goods.getId());

    }

    @Override
    public int delete(Integer id) {
        return DaoUtils.commonUpdate("delete from t_goods where id = ?",id);
    }

    @Override
    public Goods selectById(Integer id) {
        String sql = "select * from t_goods where id = ?";
        return DaoUtils.selectOne(sql,Goods.class,id);
    }

    @Override
    public List<Goods> selectList(Integer start, Integer size) {
        String sql ="SELECT g.*, gtp.gname AS gpname, gtf.gname AS gfname FROM t_goods g LEFT JOIN t_goods_type gtp ON (g.gpid = gtp.id) LEFT JOIN t_goods_type gtf ON (g.gfid = gtf.id) LIMIT ?,?";
        return DaoUtils.commonQuery(sql,Goods.class,start,size);
    }

    @Override
    public int selectCount() {
        return DaoUtils.commonCount("select count(1) from t_goods");
    }

    @Override
    public List<Goods> getAll() {
        String sql ="select * from t_goods";
        return DaoUtils.commonQuery(sql,Goods.class);
    }
}
