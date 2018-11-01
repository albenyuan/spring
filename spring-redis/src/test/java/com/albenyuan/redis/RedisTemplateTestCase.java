package com.albenyuan.redis;

import com.albenyuan.redis.service.RedisService;
import org.junit.After;
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
@ContextConfiguration("classpath:spring-redis-template.xml")
public class RedisTemplateTestCase extends BaseRedisTest {

    private static Logger logger = LoggerFactory.getLogger(RedisTemplateTestCase.class);

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    public void incr() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, 1);
        redisService.incr(KEY);
        Assert.assertTrue(String.valueOf(2).equals(redisService.get(KEY)));
    }

    @Test
    public void decr() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, 1);
        redisService.decr(KEY);
        Assert.assertTrue(Integer.valueOf(0).equals(redisService.get(KEY, Integer.class)));
        Assert.assertTrue(String.valueOf(0).equals(redisService.get(KEY)));
    }

    @Test
    public void decrExpire() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, 1);
        redisService.isExist(KEY);
        redisService.decr(KEY);
        Assert.assertTrue(String.valueOf(0).equals(redisService.get(KEY)));
    }

}
