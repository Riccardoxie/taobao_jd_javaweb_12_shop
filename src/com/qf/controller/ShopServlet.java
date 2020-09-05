package com.qf.controller;

import com.alibaba.druid.util.StringUtils;
import com.qf.doamin.GoodsDoamin;
import com.qf.entity.*;
import com.qf.service.*;
import com.qf.service.impl.*;
import com.qf.utils.Constant;
import com.qf.utils.FileUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@WebServlet(value = "/ShopServlet/*")
public class ShopServlet extends  DispatcherServlet {

    private IGoodsTypeService gtService = new GoodsTypeServiceImpl();

    private IGoodsService goodsService = new GoodsServiceImpl();

    private IAddressService addressService = new AddressServiceImpl();

    private IOrderService orderService = new OrderServiceImpl();

    private IOrderDetailService orderDetailService = new OrderDetailServiceImpl();

    public String toIndexPage(HttpServletRequest request){

        // 1.查询所有的商品类别集合
        List<GoodsType> gtList = gtService.getALl();

        // 2.查询所有的商品信息
        List<Goods> giList = goodsService.getALl(); // GoodsInfo

        // 3.把两个集合放入到req作用域
        request.setAttribute("gtList",gtList);
        request.setAttribute("giList",giList);

        initShopCar(request);

        return  Constant.FORWARD+":index.jsp";
    }

    private void initShopCar(HttpServletRequest request) {

        ShopCar shopCar = ShopCar.getUserShopCar(request.getSession());

        // 设置初始化购物车中的商品id
        Integer[] gids = {26,29,30,31};

        for(int i =0;i<gids.length;i++){

            GoodsDoamin goodsDoamin = addGoodsDomain(gids[i], (i + 1) * 2);

            shopCar.add(goodsDoamin);
        }
    }

    public String getGoodsInfoById(Integer id,HttpServletRequest request){

         // 1.根据id查询商品信息
        Goods goods = goodsService.selectById(id);

        request.setAttribute("goodsInfo",goods);

        // 3.跳转到单个商品展示页面
        return  Constant.FORWARD+":introduction.jsp";
    }

    public void getGoodsPngById(Integer id,HttpServletResponse response){

        // 1.根据id查询对象
        Goods goods = goodsService.selectById(id);

        // 2.图片的决定路径
        String gpng = goods.getGpng();

        FileInputStream ips=null;
        try {

            // 3.读取文件流
            ips = new FileInputStream(gpng);

            // 4.把文件流拷贝到response中
            IOUtils.copy(ips,response.getOutputStream());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(ips);
        }
    }

    public void addShopCar(Integer gid,Integer count,HttpServletRequest request){

        // 1.获取购车对象
        ShopCar shopCar = ShopCar.getUserShopCar(request.getSession()); // 这个方法做判断，如果是第一次创建购物车对象，如果是第二次送session中获取

        // 2.把商品封装成一个goodsDomain
        GoodsDoamin goodsDoamin= addGoodsDomain(gid,count);

        // 3.把用户商品添加到购物车
        shopCar.add(goodsDoamin);
    }

    private GoodsDoamin addGoodsDomain(Integer gid, Integer count) {

        // 1.创建GoodsDoamin
        GoodsDoamin goodsDoamin = new GoodsDoamin();

        // 2.赋值商品的数量,当前这里还差一部分的值
        goodsDoamin.setCount(count);

        // 3.根据商品id查询商品对象
        Goods goods = goodsService.selectById(gid);

        // 4.把goods中的属性拷贝到goodsDomain中(两个对象的属性一直才能拷贝)
        try {
            BeanUtils.copyProperties(goodsDoamin,goods);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return goodsDoamin;
    }

    public void updateShop(Integer gid,Integer count,HttpServletRequest request){

        // 得到购物车对象
        ShopCar shopCar= ShopCar.getUserShopCar(request.getSession());

        // 修改某个商品数量
        shopCar.update(gid,count);
    }

    public void deleteShopCar(Integer id,HttpServletRequest req){

        ShopCar shopCar = ShopCar.getUserShopCar(req.getSession());

        shopCar.delete(id);

    }

    // 从购物车跳转到支付页面
    public String toPay(HttpServletRequest request){

        // 1.获取登录的用户
        Customer customer = (Customer)request.getSession().getAttribute(Constant.FONT_USER);

        if(customer == null){
            return Constant.REDIRECT+":login.jsp?msg=xxxxxx";
        }

        // 2.查询当前用户的收货地址
        List<Address> userAddress =addressService.getAddressListByUid(customer.getId());

        // 3.添加到req作用域
        request.setAttribute("userAddressList",userAddress);

        // 4.跳转到支付页面
        return  Constant.FORWARD+":pay.jsp";

    }

    public String addOrder(Order order,HttpServletRequest request){
        // 1.地址信息，物流信息，支付信息

        // 2.获取当前的登录的用户
        Customer customer =(Customer) request.getSession().getAttribute(Constant.FONT_USER);

        if(customer == null){

            return Constant.REDIRECT+":login.jsp";
        }

        // 3.获取当前用户的购物车对象
        ShopCar shopCar = ShopCar.getUserShopCar(request.getSession());

        System.out.println(order);
        order.setStatus(1); // 未支付
        order.setUid(customer.getId());
//        order.setCreateTime(newDae); // 下单时间在dao层处理
        order.setTotalPrice(shopCar.getTotalPrice());

        // 4.添加订单
        int oid= orderService.insert(order); // 返回订单id

        // 5.添加订单详情(有多个
        List<OrderDetail> orderDetails = new ArrayList<>();

        // 6.遍历购物车
        List<GoodsDoamin> list = shopCar.getList();
        for(GoodsDoamin gd:list){

            // 7.创建一个商品详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setGdesc(gd.getGdesc());
            orderDetail.setGid(gd.getGfid());
            orderDetail.setGname(gd.getGname());
            orderDetail.setGpng(gd.getGpng());
            orderDetail.setGprice(gd.getGpriceOff());
            orderDetail.setCount(gd.getCount());
            orderDetail.setTotal(gd.getCount()*gd.getGpriceOff());
            orderDetail.setOid(oid); // 订单ID?

            // 8.添加到商品详情集合
            orderDetails.add(orderDetail);
        }

        // 订单详情
        orderDetailService.batchInsert(orderDetails);

        // 跳转到支付页面(第三方)对接支付宝

        // 清空用户的购物车
        shopCar.clear(request.getSession());

        // 跳转到支付成功页面
        return Constant.FORWARD+":success.jsp";
    }
}
