package com.qf.service;

import com.qf.entity.Page;

import java.util.List;

public interface IBaseService<T> {

    public int insert(T t);

    public int update(T t);

    public int delete(Integer id);

    public T selectById(Integer id);

    /**
     * 分页的对象
     * @param page 分页对象
     * @return 集合
     */
    public void selectPage(Page page);

    public List<T> getALl();

}
