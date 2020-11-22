package com.wh.service;

import com.wh.bean.Province;
import com.wh.dao.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    ProvinceDao provinceDao;

    @Override
    public void update(Province province) {
        Province exist = getByName(province);
        provinceDao.update(province);
    }

    @Override
    public Province getByName(Province province) {
         return provinceDao.getByName(province);
    }

    @Override
    public Province getById(Province province) {
        return provinceDao.getById(province);
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            provinceDao.delete(id);
        }else
            throw new RuntimeException("删除出现错误！");
    }

    @Override
    public void add(Province province) {
        if (province.getName()!=null){
            Province exist = provinceDao.getByName(province);
            if (exist==null){
                province.setPlacecounts(0);
                provinceDao.add(province);
                return;
            }
        }
        throw new RuntimeException("省份已经存在！");
    }


    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return provinceDao.findByPage(start,rows);
    }

    @Override
    public Integer findTotal() {
        return  provinceDao.findTotal();
    }
}
