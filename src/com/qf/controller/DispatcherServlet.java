package com.qf.controller;

import com.alibaba.druid.util.StringUtils;
import com.qf.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DispatcherServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取用户调用action
        String actionName = getActionNameByServletRequest(req.getRequestURI());

        if(!StringUtils.isEmpty(actionName)){

            // 2.根据ActoinName找到aciton(方法)
            Method  actioMethod = getActionMethodByActionName(actionName);

            if(actioMethod != null){

                // 3.处理方法的参数
                Object[] args = actionMethodParam(actioMethod,req,resp);

                try {
                    // 4.调用方法
                    Object invoke = actioMethod.invoke(this, args);

                    if(invoke != null) {
                        // 5.处理用户的响应
                        responseClinet(invoke.toString(), req, resp);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }else{
                System.out.println("你还没有定义【"+actionName+"】");
            }

        }else{
            System.out.println("在url中没有写action");
        }
    }

    private void responseClinet(String result, HttpServletRequest req, HttpServletResponse resp) {

        String[] split = result.split(":");
        String type = split[0];
        String page = split[1];

        if(Constant.FORWARD.equals(type)){

            // 转发
            try {
                req.getRequestDispatcher(req.getContextPath()+"/"+page).forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(Constant.REDIRECT.equals(type)){ // 重定向
            try {
                resp.sendRedirect(req.getContextPath()+"/"+page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object[] actionMethodParam(Method actioMethod, HttpServletRequest req, HttpServletResponse resp) {

        // 1.准备一个数组,数组的长度就是方法形参的个数
        Object[] args = new Object[actioMethod.getParameterCount()];

        // 2.获取方法形参对象
        Parameter[] parameters = actioMethod.getParameters();

        for(int i =0;i<parameters.length;i++){

            // 3.获取方法形参对象
            Parameter parameter = parameters[i];

            Object value = null;

            // 第一次先判断形参形参是否是req
            if(Constant.HTTP_SERVLET_REQUSET.equals(parameter.getType().getSimpleName())){
                value=req;
            }else if(Constant.HTTP_SERVLET_RESPONSE.equals(parameter.getType().getSimpleName())){
                value=resp;
            }else{
                // 4.根据方法的名称到req中获取表单的数据
                value = parameterType(req.getParameter(parameter.getName()),parameter.getType());

                if(value == null || "".equals(value)){
                    try {

                        // 如果是对象就要实例化
                        Object ins = parameter.getType().newInstance();

                        // 获取表单中的所有的数据
                        Map<String, String[]> parameterMap = req.getParameterMap();

                        try {
                            // 可以直接把Map中的数据拷贝到对象中

                            BeanUtils.populate(ins, parameterMap);
                        }catch(ConversionException e){
                            Map<String,String[]> tempMap = copeMap(parameterMap);


                            //找到Map中的时间，设置为null
                            Map<String,Object> dataMap = setDateNull(tempMap);
                            BeanUtils.populate(ins,tempMap);
                            //找到实体类中的时间类型，赋值
                            beanDateFiled(dataMap,ins);


                        }

                        // 把实例化对象赋值给value
                        value = ins;

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 5.把value放入数组中
            args[i]=value;

        }
        return args;
    }

    private Map<String, String[]> copeMap(Map<String, String[]> parameterMap) {
        Map<String,String[]> map = new HashMap<>();

        Set<Map.Entry<String,String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String,String[]>entry:entries){
            map.put(entry.getKey(),entry.getValue());
        }

        return map;

    }

    private void beanDateFiled(Map<String,Object> dataMap, Object ins) {
        //获取时间类型名称
        Object name = dataMap.get("name");
        Object value = dataMap.get("value");//字符串类型的时间
        try {
            //从bean中获取时间字段
//            Field field = ins.getClass().getField(name.toString());
            Field field = ins.getClass().getDeclaredField(name.toString());

            //授权
            field.setAccessible(true);
            //赋值
//            try {
            field.set(ins,new SimpleDateFormat("yyyy-MM-dd").parse(value.toString()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



    /**
     *
     * 找到Map中的时间类型，并且赋值是Null,最后把时间返回
     * @param parameterMap
     * @return
     */
    private Map<String,Object> setDateNull(Map<String, String[]> parameterMap) {
        Map<String,Object> map = new HashMap<>();
//        String mapValue = null;
//        String mapKey = null;
        Set<Map.Entry<String,String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String,String[]> entri:entries){
            String key = entri.getKey();
            String value = entri.getValue()[0];
            //判断value是否为时间类型
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //如果转成成功，则为时间类型
                Date parse = sdf.parse(value);

                //把Map中的时间类型替换为null

//                parameterMap.put(key,null);
                parameterMap.remove(key);

                //把key,value保存
                map.put("name",key);
                map.put("value",value);
                break;
            } catch (ParseException e) {
//                e.printStackTrace();
            }


        }

        return map;
    }

    private Object parameterType(String value, Class<?> type) {

        Object val = value;

        if(Constant.INTEGER.equals(type.getSimpleName())){
            val = Integer.parseInt(value);
        }else if(Constant.DOUBLE.equals(type.getSimpleName())){
            val = Double.parseDouble(value);
        }
//        else if ("String".equals(type.getSimpleName())){
//            val = value.toString();
//        }
        return val;
    }

    private Method getActionMethodByActionName(String actionName) {

        // 1.先获取当前servlet中所有的方法
        Method[] methods = this.getClass().getMethods();

        // 2.遍历
        for(int i =0;i<methods.length;i++){

            // 3.对比方法名称
            if(methods[i].getName().equals(actionName)){ // 注意:暂时不考虑方法重载的问题

                // 4.返回action
                return methods[i];
            }
        }
        return  null;
    }

    private String getActionNameByServletRequest(String requestURI) {
        // 截取URI最后一个斜杠后的地址就成action名称
        return  requestURI.substring(requestURI.lastIndexOf("/") + 1);
    }

    public void responseString(String msg,HttpServletResponse response){
        try {

            response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String responsePage(int status,String page){
        if(status>0){
            return Constant.REDIRECT + ":"+page;
        }else{
            return Constant.FORWARD + ":error.jsp";
        }
    }
}
