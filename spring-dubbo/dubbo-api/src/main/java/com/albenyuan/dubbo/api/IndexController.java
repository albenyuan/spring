package com.albenyuan.dubbo.api;

import com.albenyuan.dubbo.mvc.BaseResult;
import com.albenyuan.dubbo.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 15:58
 */
@RestController
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference
    private UserService userService;

    @RequestMapping("")
    public BaseResult index() {
        logger.info("userService:{}", userService);
        return BaseResult.success();
    }

}
