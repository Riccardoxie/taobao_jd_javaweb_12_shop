package com.qf.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUtils {

    public static int commonUpdate(String sql,Object...args){

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst =null;
        try {

            prst = connection.prepareStatement(sql);

            if(args != null){
                for(int i=0;i<args.length;i++){
                    prst.setObject(i+1,args[i]);
                }
            }
            return prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(prst,connection);
        }
        return  0;
    }

    public static int commonInsert(String sql,Object...args){

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst =null;
        ResultSet resultSet = null;
        try {

            // Statement.RETURN_GENERATED_KEYS：需要返回生成的主键
            prst = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            if(args != null){
                for(int i=0;i<args.length;i++){
                    prst.setObject(i+1,args[i]);
                }
            }

            // 执行sql
            prst.executeUpdate();

            //获取主键
            resultSet = prst.getGeneratedKeys();

            if(resultSet.next()){
                return resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,prst,connection);
        }
        return  0;
    }

    public static <T> List<T> commonQuery(String sql, Class<T> cls, Object ...args){

        List<T> list = new ArrayList<T>();

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst =null;
        ResultSet resultSet = null;
        try {

            prst = connection.prepareStatement(sql);

            if(args != null){
                for(int i=0;i<args.length;i++){
                    prst.setObject(i+1,args[i]);
                }
            }

            resultSet = prst.executeQuery();

            while(resultSet.next()){

                T t = cls.newInstance();

                Field[] declaredFields = t.getClass().getDeclaredFields();

                for(int i =0;i<declaredFields.length;i++){

                    Field declaredField = declaredFields[i];
                    declaredField.setAccessible(true);

                    String name = declaredField.getName();

                    Object value = null;
                    try {
                        value = resultSet.getObject(name);
                    }catch (SQLException e){
                        System.err.println("结果集中没有找到【"+name+"】属性");
                    }

                    declaredField.set(t,value);
                }

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet,prst,connection);
        }
        return  list;
    }

    public static int commonCount(String sql){

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst =null;
        ResultSet resultSet = null;
        try {

            prst = connection.prepareStatement(sql);

            resultSet = prst.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DBUtils.close(resultSet,prst,connection);
        }
        return  0;
    }

    public static <T> T selectOne(String sql, Class<T>cls, Object...args){
        List<T> list = DaoUtils.commonQuery(sql, cls, args);
        if(list.isEmpty()){
            return  null;
        }else{
            return  list.get(0);
        }
    }
}
