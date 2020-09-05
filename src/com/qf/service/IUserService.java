package com.qf.service;

import com.qf.entity.User;

public interface IUserService extends  IBaseService<User>{
    User selectUserByEmail(String email);

    int batchDel(String[] ids);

    User login(String username, String password);
}
