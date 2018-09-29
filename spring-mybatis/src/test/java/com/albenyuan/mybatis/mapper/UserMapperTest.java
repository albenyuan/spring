package com.albenyuan.mybatis.mapper;

import com.albenyuan.core.data.SpringBaseTestCase;
import com.albenyuan.mybatis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author Alben Yuan
 * @Date 2018-03-29 15:22
 */

@Rollback
@Transactional
public class UserMapperTest extends SpringBaseTestCase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;


    @Test
    public void findById() {
        User user = userMapper.findById(1l);
        Assert.assertNotNull("'user' must be not null", user);
    }
}
