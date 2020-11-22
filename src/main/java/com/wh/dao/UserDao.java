package com.wh.dao;

import com.wh.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    void saveUser(User user);
    User getUserByName(String username);
}
