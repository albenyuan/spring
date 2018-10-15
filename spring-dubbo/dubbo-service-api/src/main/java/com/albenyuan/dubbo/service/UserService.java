package com.albenyuan.dubbo.service;

import com.albenyuan.dubb.common.BaseService;
import com.albenyuan.dubbo.data.model.User;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 10:46
 */
public interface UserService extends BaseService {

    User login(String username, String password);
}
