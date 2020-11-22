package com.wh.config;

import com.wh.bean.User;
import com.wh.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;



    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user = userDao.getUserByName(username);
        if (user==null) return null;

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username,               //用户名
                user.getPassword(),      //从数据库取出的安全密码
                getName()               //自定义realm的名字
        );

        return info;
    }
}
