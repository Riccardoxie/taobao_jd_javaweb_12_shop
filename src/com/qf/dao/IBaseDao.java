package com.qf.dao;

import java.util.List;

public interface IBaseDao<T> {

    /**
     * 插入操作
     * @param t 被插入的对象
     * @return 影响的行数
     */
    public int insert(T t);

    public int update(T t);

    /**
     * 删除操作
     * @param id 被删除的id
     * @return 响应的行数
     */
    public int delete(Integer id);

    /**
     * 根据id查询对象
     * @param id 查询的id
     * @return 对象
     */
    public T selectById(Integer id);

    /**
     * 分页的擦洗混
     * @param start 其实的行数
     * @param size 偏移量
     * @return 集合
     */
    public List<T> selectList(Integer start,Integer size);

    /**
     * 总条数
     * @return
     */
    public int selectCount();

    /**
     * 查询全部
     * @param <T>
     * @return
     */
    <T> List<T> getAll();
}
