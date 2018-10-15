package com.albenyuan.dubbo.api;

import com.albenyuan.dubbo.mvc.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 15:58
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("")
    public BaseResult index() {
        return BaseResult.success();
    }

}
