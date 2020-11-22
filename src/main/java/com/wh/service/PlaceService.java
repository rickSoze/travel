package com.wh.service;



import com.wh.bean.Place;
import com.wh.bean.Province;

import java.util.List;

public interface PlaceService {

    List<Place> findByPage(Integer page, Integer rows,Integer provinceid);

    Integer findTotal();

    void add(Place place);

    Place getByName(Place place);
    Place getById(Place place);

    void update(Place place);

    void delete(String id);
}
