package com.albenyuan.spring.jpa.service.impl;

import com.albenyuan.spring.jpa.entity.User;
import com.albenyuan.spring.jpa.repository.UserRepository;
import com.albenyuan.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alben Yuan
 * @Date 2019-06-04 19:57
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user, int step) {

        user.setPhone("1");
        userRepository.save(user);
        if (2 == step) {
            throw new RuntimeException("2");
        }
        user.setPhone(String.valueOf(step));
        return userRepository.save(user);
    }
}
