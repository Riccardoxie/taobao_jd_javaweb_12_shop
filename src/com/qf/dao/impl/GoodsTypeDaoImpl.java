package com.qf.dao.impl;

import com.qf.dao.IGoodsTypeDao;
import com.qf.entity.GoodsType;
import com.qf.entity.User;
import com.qf.utils.DaoUtils;

import java.util.List;

public class GoodsTypeDaoImpl implements IGoodsTypeDao {

    @Override
    public int insert(GoodsType goodsType) {
        String sql ="insert into t_goods_type(gname,gpng,gpid) values(?,?,?)";
        return DaoUtils.commonUpdate(sql,goodsType.getGname(),goodsType.getGpng(),goodsType.getGpid());
    }

    @Override
    public int update(GoodsType goodsType) {
        String sql ="update t_goods_type set gname =?,gpng =?,gpid =? where id = ?";
        return DaoUtils.commonUpdate(sql,goodsType.getGname(),goodsType.getGpng(),goodsType.getGpid());

    }



    @Override
    public int delete(Integer id) {

        return DaoUtils.commonUpdate("delete from t_goods_type where id = ?",id);
    }


    @Override
    public GoodsType selectById(Integer id) {
        String sql ="select * from t_goods_type where id = ?";
        List<GoodsType> goodsTypes = DaoUtils.commonQuery(sql, GoodsType.class, id);
        if(goodsTypes.isEmpty()){
            return  null;
        }else{
            return  goodsTypes.get(0);
        }
    }

    @Override
    public List<GoodsType> selectList(Integer start, Integer size) {
        String sql ="SELECT t.*, ifnull(p.gname, '无') AS gpname FROM t_goods_type t LEFT JOIN t_goods_type p ON (t.gpid = p.id) limit ?,?";
        return DaoUtils.commonQuery(sql,GoodsType.class,start,size);
    }

    @Override
    public int selectCount() {
        String sql ="select count(1) from t_goods_type";
        return DaoUtils.commonCount(sql);
    }

    @Override
    public List<GoodsType> getAll() {
        String sql ="select * from t_goods_type";
        return DaoUtils.commonQuery(sql,GoodsType.class);
    }

    @Override
    public List<GoodsType> getGoodsListByPid(Integer pid) {
        String sql ="select * from t_goods_type where gpid = ?";
        return DaoUtils.commonQuery(sql,GoodsType.class,pid);
    }
}
