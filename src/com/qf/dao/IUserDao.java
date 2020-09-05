package com.qf.dao;

import com.qf.entity.User;

public interface IUserDao extends  IBaseDao<User>{
    User selectUserByEmail(String email);

    int batchDel(String[] ids);

    User login(String username, String password);
}
