package com.qf.controller;

import com.alibaba.druid.util.StringUtils;
import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.service.IGoodsService;
import com.qf.service.impl.GoodsServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/GoodsServlet/*")
public class GoodsServlet extends  DispatcherServlet {

    private IGoodsService goodsService = new GoodsServiceImpl();

    public String getGoodsPage(Page<Goods> page, HttpServletRequest request){
        goodsService.selectPage(page);
        page.setUrl("GoodsServlet/getGoodsPage");
        request.setAttribute("page",page);
        return Constant.FORWARD+":back/goods/goodsList.jsp";
    }

    public String getGoodsById(Integer id,HttpServletRequest request){
        Goods goods = goodsService.selectById(id);
        request.setAttribute("goods",goods);
        return Constant.FORWARD+":back/goods/goodsupdate.jsp";
    }

    public String updateGoods(HttpServletRequest request){
        Goods goods = uploadFile(request);
        int update = goodsService.update(goods);
        return  responsePage(update,"GoodsServlet/getGoodsPage");
    }
    public String deleteGoodsById(Integer id){
        return  responsePage(goodsService.delete(id),"GoodsServlet/getGoodsPage");
    }


    public Goods uploadFile(HttpServletRequest request){

        // 把Map中的数据拷贝到对象中
        Goods goods = new Goods();

        // 设置文件文件上传的地址
        String serverPath = request.getServletContext().getRealPath("images");

        // 1.创建一个文件上传的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 2.文件上传的核心类
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 3.解析req
        try {
            List<FileItem> fileItemList= upload.parseRequest(request);

            // 创建一个Map用来存表单中的数据
            Map<String,Object> map = new HashMap<>();

            // 4.遍历
            for (FileItem fileItem:fileItemList) {

                // 5.判断是否是文本
                if(fileItem.isFormField()){
                    // key对应的是表单中的name属性，value就是表单的值
                    map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
                }else{

                    String name = fileItem.getName(); // 文件名称
                    if(StringUtils.isEmpty(name)){
                        continue;
                    }

                    String fieldName = fileItem.getFieldName(); // 表单中属性名称

                    String contentType = fileItem.getContentType(); // 文件类型

                    // 获取新的文件名称
                    String newFileName = FileUtils.getNewFileName(name);

                    // 定义输出流
                    FileOutputStream ops = null;
                    File file = new File(serverPath+ File.separator+newFileName);
                    try {
                        ops = new FileOutputStream(file);

                        // IO流拷贝
                        IOUtils.copy(fileItem.getInputStream(),ops);

                        // 把图片的绝对路径和图片的名称包含到Map中
                        map.put(fieldName,file.getAbsoluteFile()); // 保存文件的决定路径
                        map.put("fileName",name);





                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.closeQuietly(ops);
                    }
                }
            }
            BeanUtils.populate(goods,map);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public String addGoods(HttpServletRequest request, HttpServletResponse response){

        Goods goods = uploadFile(request);
        int insert = goodsService.insert(goods);
        return responsePage(insert,"GoodsServlet/getGoodsPage");
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

    public void showDefaultPng(HttpServletRequest request,HttpServletResponse response){

        // 1.获取图片的服务路径
        String filePathServer = request.getServletContext().getRealPath(Constant.FILE_PATH_SERVER);

        FileInputStream ips = null;
        try {
            ips = new FileInputStream(filePathServer+File.separator+Constant.DEFAULT_SHOW_PNG);
            IOUtils.copy(ips,response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(ips);
        }
    }

}
