package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.dao.IUserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;

import java.util.List;

public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public int batchDel(String[] ids) {
        return userDao.batchDel(ids);
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    protected IBaseDao<User> baseDao() {
        return userDao;
    }
}
