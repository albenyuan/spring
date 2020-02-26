package com.albenyuan.spring.shiro.config;

import com.albenyuan.spring.shiro.shiro.CustomerShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:27
 */
@Configuration
public class ShiroConfiguration {


    public static final String FILTER_AUTHC = "authc";

    public static final String FILTER_ANNO = "anon";

    public static final String FILTER_ROLES = "roles";

    public static final String FILTER_USER = "user";

    public static final String FILTER_LOGOUT = "logout";


    @Bean
    public CustomerShiroRealm customerShiroRealm() {
        return new CustomerShiroRealm();
    }


    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customerShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        shiroFilterFactoryBean.setFilters();
        Map<String, String> map = new HashMap<>();

        map.put("/logout", FILTER_LOGOUT);

        map.put("/api/v1/account/*", FILTER_ANNO);


        map.put("/api/**", FILTER_USER);

        map.put("/**", FILTER_AUTHC);
        //登录
        shiroFilterFactoryBean.setLoginUrl("/api/v1/account/unauthorized");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/api/v1/account/success");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/v1/account/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
