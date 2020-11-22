package com.wh.config;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public MyRealm getMyRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);

        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();

        //过滤器需要一个SecurityManager才能进行相关的安全操作,这里传入一个web专用的
        filter.setSecurityManager(defaultWebSecurityManager);

        /*  通过map设置拦截规则
           anon     匿名用户可访问
           authc    认证用户可访问
           user     使用rememberMe的用户可访问
           perms    对应权限可访问
           role     对应角色可访问
        */

        HashMap<String, String> filterMap = new HashMap<>();
        filterMap.put("/**","anon");
//        filterMap.put("/user/login","anon");
//        filterMap.put("/user/register","anon");
//
//        filterMap.put("/static/js","anon");
//        filterMap.put("/static/img","anon");
//        filterMap.put("/static/css","anon");
//        filterMap.put("/static/login.html","anon");
//        filterMap.put("/static/register.html","anon");
//        filterMap.put("/**","authc");
//
//        filterMap.put("/logout","logout");
//
//        filter.setFilterChainDefinitionMap(filterMap);
//        filter.setLoginUrl("/static/login.html");
//        filter.setUnauthorizedUrl("/lackPermission");

        return filter;

    }
}
