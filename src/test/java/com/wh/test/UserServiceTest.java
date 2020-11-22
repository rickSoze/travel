package com.wh.test;


import com.wh.TravelApplication;
import com.wh.bean.User;
import com.wh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void test(){
        userService.register(new User(1,"wh","123","123@qq.com"));
    }

}
