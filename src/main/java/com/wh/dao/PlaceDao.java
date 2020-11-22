package com.wh.dao;

import com.wh.bean.Place;
import com.wh.bean.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceDao extends BaseDao<Place,String>{

    @Override
    void add(Place place);

    @Override
    Place getByName(Place place);

    @Override
    Place getById(Place place);

    @Override
    void update(Place place);

    @Override
    void delete(String s);

    @Override
    List<Place> findAll();

    List<Place> findByPage(@Param("start") Integer start, @Param("rows")Integer rows, @Param("provinceid")Integer provinceid);

    Integer findTotal(String provinceid);
}
