package com.qf.entity;

import com.qf.doamin.GoodsDoamin;
import com.qf.utils.Constant;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShopCar {

    private List<GoodsDoamin> list = new ArrayList<>(); // 用来方所有的商品

    public List<GoodsDoamin> getList(){
        return  this.list;
    }

    /**
     * 添加商品到购物车
     * @param goodsDoamin
     */
    public void add(GoodsDoamin goodsDoamin){

        // 添加的商品是否存在
        GoodsDoamin temp = isExist(goodsDoamin);

        if(temp != null){
            // 说明这个商品已经存在，修改商品的数量
            update(goodsDoamin.getId(),goodsDoamin.getCount()+temp.getCount());
        }else{
            list.add(goodsDoamin);
        }
    }

    private GoodsDoamin isExist(GoodsDoamin goodsDoamin) {
        for (GoodsDoamin gd: list) {
            if(gd.getId().equals(goodsDoamin.getId())){
                return gd;
            }
        }
        return null;
    }

    /**
     * 删除购物车
     * @param goodsDoamin
     */
    public void remove(GoodsDoamin goodsDoamin){
        list.remove(goodsDoamin);
    }


    public static ShopCar getUserShopCar(HttpSession session) {

        // 1.定义购车对象
        ShopCar shopCar = null;

        // 2.先从session中获取购车对象
        shopCar = (ShopCar) session.getAttribute(Constant.SHOP_CAR);

        if(shopCar == null){
            // 说明用户的session中没有购物车,创建一个新的购车
            shopCar = new ShopCar();

            // 放入sesion中
            session.setAttribute(Constant.SHOP_CAR,shopCar);
        }

        return shopCar;
    }

    public int getTotalCount(){
        int count = 0; // 局部变量必须要初始化
        for (GoodsDoamin  gd:list) {
            count+=gd.getCount();
        }
        return count;
    }

    public void update(Integer gid, Integer count) {
        for (GoodsDoamin gd: list) {
            if(gd.getId().equals(gid)){
                gd.setCount(count);
                break;
            }
        }
    }

    public void delete(Integer id) {
        for(GoodsDoamin gd:list){
            if(gd.getId().equals(id)){
                list.remove(gd);
                break;  // 集合在遍历的时候不能改变结合的结构
            }

        }
    }

    public Double getTotalPrice(){
        System.out.println("ShopCar.getTotalPrice");
        Double price = 0.0;
        for (GoodsDoamin gd:list) {
            price+=gd.getCount()*gd.getGpriceOff();
        }
        return  price;
    }

    public void clear(HttpSession session) {
        session.removeAttribute(Constant.SHOP_CAR);
    }
}
