package com.qf.service.impl;

import com.qf.dao.IBaseDao;
import com.qf.entity.Page;
import com.qf.service.IBaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    // 这个baseDao需要子类来来实例化
    protected abstract IBaseDao<T> baseDao();// ?需不需要实例化

    @Override
    public int insert(T t) {
        return baseDao().insert(t);
    }

    @Override
    public int update(T t) {
        return baseDao().update(t);
    }

    @Override
    public int delete(Integer id) {
        return baseDao().delete(id);
    }

    @Override
    public T selectById(Integer id) {
        return baseDao().selectById(id);
    }

    @Override
    public void selectPage(Page page) {

        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();

        int count = baseDao().selectCount();

        List<T> list = baseDao().selectList((pageNum - 1) * pageSize, pageSize);

        page.setTotalCount(count);
        page.setList(list);

    }

    @Override
    public List<T> getALl() {
        return baseDao().getAll();
    }
}
