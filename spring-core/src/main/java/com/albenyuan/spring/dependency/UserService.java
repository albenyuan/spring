package com.albenyuan.spring.dependency;

import com.albenyuan.spring.autowire.User;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Alben Yuan
 * @Date 2018-09-25 15:39
 */
@Slf4j
@Setter
public class UserService implements InitializingBean {

    @Autowired
    private UserDao userDao;

    public UserService() {
        log.info("UserService()");
    }

    public User findOne() {
        return userDao.findOne();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("UserService afterPropertiesSet");
    }
}
