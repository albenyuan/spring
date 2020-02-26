package com.albenyuan.spring.thymeleaf.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Alben Yuan
 * @Date 2019-04-29 13:29
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("username", "username");
        model.addAttribute("user", ImmutableMap.of("username", "albenyuan", "email", "albenyuan@aliyun.com"));
        model.addAttribute("list", Stream.iterate(0, UnaryOperator.identity()).limit(100).collect(Collectors.toList()));
        return "login";
    }

    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomNumeric(4));
    }
}
