package com.wh.service;

import com.wh.bean.Province;
import com.wh.bean.User;

import java.util.List;

public interface ProvinceService {

    List<Province> findByPage(Integer page,Integer rows);

    Integer findTotal();

    void add(Province province);

    Province getByName(Province province);
    Province getById(Province province);

    void update(Province province);

    void delete(String id);
}
