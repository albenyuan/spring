package com.albenyuan.spring.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:49
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {


    @GetMapping("/login")
    public Object login(String username, String password) {
        return "not login";
    }
}
