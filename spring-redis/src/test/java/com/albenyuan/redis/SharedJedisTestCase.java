package com.albenyuan.redis;

import com.albenyuan.redis.entity.User;
import com.albenyuan.redis.service.RedisService;
import com.albenyuan.spring.test.core.SpringJUnit4Case;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

/**
 * @Author Alben Yuan
 * @Date 2018-10-18 14:51
 */
@ContextConfiguration("classpath:spring-shared-jedis.xml")
public class SharedJedisTestCase extends BaseRedisTest {

    private static Logger logger = LoggerFactory.getLogger(SharedJedisTestCase.class);

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


}
