package com.wh.service;

import com.wh.bean.User;
import com.wh.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public  class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public void checkLogin(User user) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
    }

    @Override
    public void register(User user){
        if (userDao.getUserByName(user.getUsername())==null){
            userDao.saveUser(user);
        }else {
            throw  new RuntimeException("用户已经存在！");
        }
    }
}
