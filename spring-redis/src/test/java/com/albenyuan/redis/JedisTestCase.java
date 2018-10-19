package com.albenyuan.redis;

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
@ContextConfiguration("classpath:spring-jedis.xml")
public class JedisTestCase extends SpringJUnit4Case {

    private static Logger logger = LoggerFactory.getLogger(JedisTestCase.class);

    private static String KEY = "TEST-KEY";

    @Autowired
    private RedisService redisService;

    @Test
    public void save() throws IOException {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, KEY);
        Assert.assertTrue(KEY.equals(redisService.get(KEY)));
    }

    @Test
    public void incr() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, String.valueOf(1));
        redisService.incr(KEY);
        Assert.assertTrue("2".equals(redisService.get(KEY)));
    }

    @Test
    public void decr() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, String.valueOf(1));
        redisService.decr(KEY);
        Assert.assertTrue("0".equals(redisService.get(KEY)));
    }

    @Test
    public void decrExpire() {
        redisService.delete(KEY);
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, 100, String.valueOf(1));
        redisService.decr(KEY);
        Assert.assertTrue("0".equals(redisService.get(KEY)));
    }
}
