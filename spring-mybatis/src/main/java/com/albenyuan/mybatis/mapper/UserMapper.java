package com.albenyuan.mybatis.mapper;

import com.albenyuan.core.data.mapper.BaseMapper;
import com.albenyuan.mybatis.model.User;
import org.springframework.stereotype.Repository;

/**
 * @Author Alben Yuan
 * @Date 2018-09-28 17:21
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    User findById(Long id);

}
