package com.albenyuan.spring.shiro.service;

import com.albenyuan.spring.shiro.entity.Role;

import java.util.stream.Stream;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:12
 */
public interface RoleService {


    Stream<Role> findByUserId(Long userId);

}
