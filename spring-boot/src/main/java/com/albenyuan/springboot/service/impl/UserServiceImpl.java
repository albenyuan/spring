package com.albenyuan.springboot.service.impl;

import com.albenyuan.springboot.domain.User;
import com.albenyuan.springboot.mapper.UserMapper;
import com.albenyuan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 14:41
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User save(User user) {
        Integer i = userMapper.insert(user);
        return i > 0 ? userMapper.selectOneByExample(user) : null;
    }
}
