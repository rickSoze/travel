package com.wh.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T,K> {
    void add(T t);
    T getByName(T t);
    T getById(T t);
    void update(T t);
    void delete(K k);


    List<T> findAll();
    List<T> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);
    Integer findTotal();

}
