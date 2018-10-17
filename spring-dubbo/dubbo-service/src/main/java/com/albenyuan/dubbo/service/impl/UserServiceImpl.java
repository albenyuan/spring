package com.albenyuan.dubbo.service.impl;

import com.albenyuan.dubbo.service.UserService;
import com.albenyuan.dubbo.data.model.User;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 10:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User login(String username, String password) {

        if (null == username || null == password) {
            return null;
        }
        User user = new User();
        user.setPassword(username);
        user.setUsername(username);
        return user;
    }
}
