package com.albenyuan.dubbo.api;

import com.albenyuan.dubbo.mvc.BaseController;
import com.albenyuan.dubbo.mvc.BaseResult;
import com.albenyuan.dubbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 10:42
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

//    @Autowired
//    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping("/list")
    public BaseResult list() {
        logger.info("success");
        return BaseResult.success();
    }


}
