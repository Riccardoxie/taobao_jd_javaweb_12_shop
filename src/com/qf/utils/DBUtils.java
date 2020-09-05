package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();

        try {


            // 1.加载数据库配置文件
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            // 2.初始化连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            // 从连接池中获取连接
            return  dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void close(AutoCloseable ...autoCloseable){
        if(autoCloseable !=null){
            for(int i =0;i<autoCloseable.length;i++){
                if(autoCloseable[i] != null){
                    try {
                        autoCloseable[i].close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
