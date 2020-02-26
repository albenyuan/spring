package com.albenyuan.spring.jpa.service;

import com.albenyuan.spring.jpa.entity.User;

/**
 * @Author Alben Yuan
 * @Date 2019-06-04 19:56
 */
public interface UserService {

    User save(User user, int step);

}
