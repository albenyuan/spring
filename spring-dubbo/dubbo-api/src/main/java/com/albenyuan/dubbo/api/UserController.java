package com.albenyuan.dubbo.api;

import com.albenyuan.dubbo.data.model.User;
import com.albenyuan.dubbo.mvc.BaseController;
import com.albenyuan.dubbo.mvc.BaseResult;
import com.albenyuan.dubbo.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 10:42
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;

    @RequestMapping("/list")
    public BaseResult list() {
        logger.info("success");
        return BaseResult.success();
    }

    @RequestMapping("/login")
    public BaseResult<User> login(String username, String password) {
        User user = userService.login(username, password);
        return BaseResult.success(user);
    }


}
