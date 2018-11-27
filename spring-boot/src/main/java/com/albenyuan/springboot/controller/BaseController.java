package com.albenyuan.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 10:21
 */

@RestController
@RequestMapping("/app")
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("/index")
    public Object index() {
        Integer r = 1 / 0;
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


}
