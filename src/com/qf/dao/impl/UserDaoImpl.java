package com.qf.dao.impl;

import com.qf.dao.IUserDao;
import com.qf.entity.User;
import com.qf.utils.DBUtils;
import com.qf.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    @Override
    public int insert(User user) {
        String sql ="insert into t_user(username,password,sex,age,email,png,birthday) values(?,?,?,?,?,?,?)";
        return DaoUtils.commonUpdate(sql,user.getUsername(),user.getPassword(),user.getSex(),user.getAge(),user.getEmail(),user.getPng(),user.getBirthday());

    }

    @Override
    public int update(User user) {
        String sql ="update t_user set username =?,sex =?,age =?,email =?,png =?,birthday =? where id = ?";
        return DaoUtils.commonUpdate(sql,user.getUsername(),user.getSex(),user.getAge(),user.getEmail(),user.getPng(),user.getBirthday(),user.getId());

    }

    @Override
    public int delete(Integer id) {
        return DaoUtils.commonUpdate("delete from t_user where id = ?",id);
    }

    @Override
    public User selectById(Integer id) {
        String sql ="select * from t_user where id = ?";
        List<User> users = DaoUtils.commonQuery(sql, User.class, id);
        if(users.isEmpty()){
            return  null;
        }else{
            return users.get(0);
        }
    }

    @Override
    public List<User> selectList(Integer start, Integer size) {
        String sql ="select * from t_user limit ?,?";
        List<User> users = DaoUtils.commonQuery(sql, User.class, start,size);
        return  users;
    }

    @Override
    public int selectCount() {
        String  sql ="select count(1) from t_user ";
        return DaoUtils.commonCount(sql);
    }

    @Override
    public <T> List<T> getAll() {
        return null;
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql ="select * from t_user where email = ?";
        List<User> users = DaoUtils.commonQuery(sql, User.class, email);
        if(users.isEmpty()){
            return  null;
        }else{
            return users.get(0);
        }
    }

    @Override
    public int batchDel(String[] ids) {

        Connection connection = DBUtils.getConnection();

        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement("delete from t_user where id = ?");

            for(int i =0;i<ids.length;i++){

                // 给占位符赋值
                prst.setInt(1,Integer.parseInt(ids[i]));

                // 添加到缓存区
                prst.addBatch();
            }

            // 刷新缓存区
            prst.executeBatch();

            return  1;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(prst,connection);
        }
        return  0;
    }

    @Override
    public User login(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return DaoUtils.selectOne(sql,User.class,username,password);
    }
}
