package com.wh.test;


import com.wh.TravelApplication;
import com.wh.bean.Place;
import com.wh.service.PlaceServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class PlaceServiceTest {
    @Autowired
    PlaceServiceImpl placeService;

    @Test
    public void test(){
        Place place = new Place();
        place.setProvinceid(1);
        place.setName("洛碛");
        placeService.add(place);
    }

    @Test
    public void test1(){
        placeService.delete("2","1");
    }



}
