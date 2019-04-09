package com.albenyuan.spring.shiro.service.impl;

import com.albenyuan.spring.shiro.entity.User;
import com.albenyuan.spring.shiro.repository.UserRepository;
import com.albenyuan.spring.shiro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:06
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return userRepository.findFirstByUsername(username);
    }
}
