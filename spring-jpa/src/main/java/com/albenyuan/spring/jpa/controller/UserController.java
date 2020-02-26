package com.albenyuan.spring.jpa.controller;

import com.albenyuan.spring.jpa.entity.User;
import com.albenyuan.spring.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Alben Yuan
 * @Date 2019-06-04 20:01
 */
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("")
    public Object save(@RequestBody User user, @RequestParam Integer step) {
        user.setId(null);
        return userService.save(user, step);
    }
}
