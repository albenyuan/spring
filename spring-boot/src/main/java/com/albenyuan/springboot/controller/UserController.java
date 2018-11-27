package com.albenyuan.springboot.controller;

import com.albenyuan.springboot.domain.User;
import com.albenyuan.springboot.service.UserService;
import com.albenyuan.web.core.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 11:14
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public Object login() {
        User user = new User();
        user.setAuthDate(new Date());
        user.setBirthday(new Date());
        user.setName("Alben Yuan");
        user.setPassword("Password");
        user.setAuth(true);
        user.setUsername("albenyuan");
        return BaseResult.success(user);
    }

    @GetMapping("add")
    public BaseResult add() {
        User user = new User();
        user.setAuthDate(new Date());
        user.setBirthday(new Date());
        user.setName("Alben Yuan");
        user.setPassword("Password");
        user.setAuth(true);
        user.setUsername("albenyuan");
        user = userService.save(user);
        return BaseResult.success(user);
    }
}
