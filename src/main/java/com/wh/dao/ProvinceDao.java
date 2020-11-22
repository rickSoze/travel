package com.wh.dao;

import com.wh.bean.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProvinceDao extends BaseDao<Province,String>{
    @Override
    void add(Province province);

    @Override
    Province getByName(Province province);

    @Override
    Province getById(Province province);

    @Override
    void update(Province province);

    @Override
    void delete(String id);

    @Override
    List<Province> findAll();

    @Override
    List<Province> findByPage(@Param("start")Integer start, @Param("rows")Integer rows);

    @Override
    Integer findTotal();
}
