package com.wh.test;


import com.wh.TravelApplication;
import com.wh.bean.Province;
import com.wh.bean.User;
import com.wh.service.ProvinceServiceImpl;
import com.wh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class ProvinceServiceTest {
    @Autowired
    ProvinceServiceImpl provinceService;

    @Test
    public void test(){
        List<Province> byPage = provinceService.findByPage(2, 3);
        byPage.forEach(System.out::println);
    }

    @Test
    public void test2(){
        Province province = new Province(8, "海南", "椰子", 3);
        provinceService.add(province);
        System.out.println(provinceService.getByName(province));
    }

    @Test
    public void test3(){
        Province province = new Province( 4,"广西", "海边", 3);
        provinceService.update(province);
        System.out.println(provinceService.getByName(province));
    }

}
