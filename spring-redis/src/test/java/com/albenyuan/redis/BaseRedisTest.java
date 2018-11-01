package com.albenyuan.redis;

import com.albenyuan.redis.entity.User;
import com.albenyuan.redis.service.RedisService;
import com.albenyuan.spring.test.core.SpringJUnit4Case;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author Alben Yuan
 * @Date 2018-11-01 11:34
 */
public abstract class BaseRedisTest extends SpringJUnit4Case {

    protected static String KEY = "TEST-KEY";
    protected static String KEY_USER = "TEST-KEY-USER";

    protected RedisService redisService;

    @Test
    public void save() throws IOException {
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, KEY);
        Assert.assertTrue(KEY.equals(redisService.get(KEY)));
    }

    @Test
    public void incr() {
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, String.valueOf(1));
        redisService.incr(KEY);
        Assert.assertTrue(String.valueOf(2).equals(redisService.get(KEY)));
    }

    @Test
    public void decr() {
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, String.valueOf(1));
        redisService.decr(KEY);
        Assert.assertTrue(String.valueOf(0).equals(redisService.get(KEY)));
    }

    @Test
    public void decrExpire() {
        Assert.assertFalse("数据应该已经清空", redisService.isExist(KEY));
        redisService.set(KEY, 100, String.valueOf(1));
        redisService.decr(KEY);
        Assert.assertTrue(String.valueOf(0).equals(redisService.get(KEY)));
    }

    @Test
    public void testUser() {
        User user = new User();
        user.setId(1l);
        user.setPassword("password");
        user.setUsername("username");
        redisService.set(KEY_USER, user);
        User user1 = redisService.get(KEY_USER, User.class);
        Assert.assertTrue(user.equals(user1));
    }


    @Before
    @After
    public void after() {
        redisService.delete(KEY);
        redisService.delete(KEY_USER);
    }

}
