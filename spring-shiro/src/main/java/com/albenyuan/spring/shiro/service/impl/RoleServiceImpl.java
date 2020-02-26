package com.albenyuan.spring.shiro.service.impl;

import com.albenyuan.spring.shiro.entity.Role;
import com.albenyuan.spring.shiro.repository.RoleRepository;
import com.albenyuan.spring.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:13
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Stream<Role> findByUserId(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }
}
