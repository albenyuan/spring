package com.albenyuan.spring.shiro.controller;

import com.albenyuan.spring.shiro.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Alben Yuan
 * @Date 2019-04-24 09:11
 */
@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    @RequiresPermissions("menu")
    @RequestMapping("/")
    public Object list() {
        return Result.success();
    }

}
