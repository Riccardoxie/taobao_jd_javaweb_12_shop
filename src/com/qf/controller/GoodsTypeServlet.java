package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IGoodsTypeService;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.utils.Constant;
import org.omg.CORBA.INTERNAL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(value = "/GoodsTypeServlet/*")
public class GoodsTypeServlet extends DispatcherServlet {

    private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

    public String getGoodsTypePage(Page<GoodsType> page, HttpServletRequest request){

        // 1.给分页中的属性赋值
        goodsTypeService.selectPage(page);

        // 2.设置url
        page.setUrl("GoodsTypeServlet/getGoodsTypePage"); //

        // 3.把page翻入到作用域
        request.setAttribute("page",page);

        // 4.跳转到页面
        return Constant.FORWARD+":back/goodstype/goodstype.jsp";
    }

    public String toAddGoodsType(HttpServletRequest request){

        // 1.查询所有的商品类别
        List<GoodsType> goodsTypes = goodsTypeService.getALl();

        // 2.req
        request.setAttribute("goodsTypeList",goodsTypes);

        // 3.跳转到页面
        return  Constant.FORWARD+":back/goodstype/goodsadd.jsp";
    }


    public String addGoodsType(GoodsType goodsType){
        return responsePage(goodsTypeService.insert(goodsType),"GoodsTypeServlet/getGoodsTypePage");
    }
    public String deleteGoodsType(Integer id){
        return  responsePage(goodsTypeService.delete(id),"GoodsTypeServlet/getGoodsTypePage");
    }
    public String updateGoodsType(GoodsType goodsType){
        return responsePage(goodsTypeService.update(goodsType),"GoodsTypeServlet/getGoodsTypePage");
}


    public String getGoodsTypeById(Integer id,HttpServletRequest request){

        System.out.println("id:"+id);
        // 1.根据id查询商品类型
        GoodsType goodsType = goodsTypeService.selectById(id);

        // 2.查询所有的商品类型
        List<GoodsType> aLl = goodsTypeService.getALl();

        System.out.println("gt:"+goodsType);
        request.setAttribute("goodsTypeList",aLl);
        request.setAttribute("goodsType",goodsType);

        return Constant.FORWARD+":back/goodstype/goodstypeupdate.jsp";
    }
//test....
    public String getGoodsTypeById2(Integer id,HttpServletRequest request){

        // 1.根据id查询对象
        GoodsType goodsType = goodsTypeService.selectById(id);

        // 2.把对象放入到req作用域中
        request.setAttribute("goodsType",goodsType);

        // 3.转发到更新页面
        return  Constant.FORWARD+":back/goodstype/goodstypeupdate2.jsp";
    }

    public void getAllGoodsType(HttpServletResponse response){

        // 1.查询所有的商品类别
        List<GoodsType> goodsTypeList = goodsTypeService.getALl();

        // 2.把集合转成JSON字符串
        String jsonString = JSON.toJSONString(goodsTypeList);

        // 3.通过流的形式写响应给浏览器
        responseString(jsonString,response);
    }

    public void getGoodsListByPid(Integer id,HttpServletResponse response){

        // 1.根据pid查询
        List<GoodsType> goodsTypes = goodsTypeService.getGoodsListByPid(id);

        // 2.把集合转成JSON
        String jsonString = JSON.toJSONString(goodsTypes);

        // 3.响应
        responseString(jsonString,response);
    }


}
