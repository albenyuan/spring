package com.albenyuan.spring.shiro.service;

import com.albenyuan.spring.shiro.entity.User;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:57
 */
public interface AccountService {

    boolean login(String username, String password);

    User findByUsername(String username);

}
