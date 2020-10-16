package com.albenyuan.spring.dependency;

import com.albenyuan.spring.autowire.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class UserDao implements InitializingBean {

    public UserDao() {
        log.info("UserDao()");
    }

    public User findOne() {
        return new User();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("UserDao.afterPropertiesSet()");
    }
}
