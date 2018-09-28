package com.albenyuan.mybatis.service.impl;

import com.albenyuan.mybatis.mapper.UserMapper;
import com.albenyuan.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Alben Yuan
 * @Date 2018-09-28 17:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;




}
