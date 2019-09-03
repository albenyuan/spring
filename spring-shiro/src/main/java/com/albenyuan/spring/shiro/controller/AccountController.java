package com.albenyuan.spring.shiro.controller;

import com.albenyuan.spring.shiro.Result;
import com.albenyuan.spring.shiro.config.ShiroUtils;
import com.albenyuan.spring.shiro.entity.User;
import com.albenyuan.spring.shiro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:49
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public Object login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            log.warn("login error:", e);
            return Result.failure(300, "302");
        }
        return Result.success();
    }


    @GetMapping("/success")
    public Result<String> success() {
        return Result.success("login success.");
    }

    @GetMapping("/unauthorized")
    public Result<String> unauthorized() {
        return Result.failure(403, "unauthorized");
    }
    
    @PostMapping("/register")
    public Object register(@RequestBody User form) {
        User user = userRepository.findFirstByUsername(form.getUsername());
        if (user == null) {
            user = new User();
            user.setUsername(form.getUsername());
            user.setPassword(form.getPassword());
            ShiroUtils.encrypt(user);
            user = userRepository.save(user);
        }
        return Result.success(user);
    }
}
