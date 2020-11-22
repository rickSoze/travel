package com.wh.service;

import com.wh.bean.Place;
import com.wh.bean.Province;
import com.wh.dao.PlaceDao;
import com.wh.dao.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceDao placeDao;
    @Autowired
    ProvinceDao provinceDao;

    @Override
    public List<Place> findByPage(Integer page, Integer rows,Integer provinceid){
        int start=(page-1)*rows;
        return placeDao.findByPage(start,rows,provinceid);
    }



    public Integer findTotal(String provinceid) {
        return  placeDao.findTotal(provinceid);
    }

    @Override
    public Integer findTotal() {
        return 0;
    }

    @Override
    public void add(Place place) {
        if (place.getName()!=null){
            Place exist = placeDao.getByName(place);
            if (exist==null){
                Province province = new Province();
                province.setId(place.getProvinceid());
                province =  provinceDao.getById(province);
                province.setPlacecounts(province.getPlacecounts()+1);
                provinceDao.update(province);
                placeDao.add(place);
                return;
            }
        }
        throw new RuntimeException("景点已经存在！");
    }

    @Override
    public Place getByName(Place place) {
        return null;
    }

    @Override
    public Place getById(Place place) {
        return placeDao.getById(place);
    }

    @Override
    public void update(Place place) {
        placeDao.update(place);
    }

    @Override
    public void delete(String id) {

    }


    public void delete(String placeid,String provinceid) {
        Province province = new Province();
        province.setId(Integer.parseInt(provinceid));
        province =  provinceDao.getById(province);
        province.setPlacecounts(province.getPlacecounts()-1);
        placeDao.delete(placeid);

        provinceDao.update(province);
    }
}
