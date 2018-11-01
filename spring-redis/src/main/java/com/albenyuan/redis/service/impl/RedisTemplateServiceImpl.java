package com.albenyuan.redis.service.impl;

import com.albenyuan.redis.service.RedisService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author Alben Yuan
 * @Date 2018-10-19 16:55
 */
public class RedisTemplateServiceImpl implements RedisService {

    private final static Logger logger = LoggerFactory.getLogger(RedisTemplateServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean isExist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean delete(String keys) {
        redisTemplate.delete(keys);
        return true;
    }

    @Override
    public boolean setnx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public Long decr(String key) {
        return redisTemplate.opsForValue().increment(key, -1);
    }

    @Override
    public boolean set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return false;
    }

    @Override
    public <T extends Serializable> boolean set(String key, T t) {
        redisTemplate.opsForValue().set(key, t);
        return true;
    }

    @Override
    public boolean set(String key, int seconds, String value) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public String get(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        return null == object ? null : object.toString();
    }

    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz) {
        Object object = redisTemplate.opsForValue().get(key);
        logger.debug("T:{}", object);
        return (T) object;
    }

    @Override
    public boolean setMap(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    @Override
    public <T extends Serializable> boolean setMapObject(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    @Override
    public List<String> getMap(String key, String field) {
        List<String> list = new ArrayList<>();
        list.add((String) redisTemplate.opsForHash().get(key, field));
        return list;
    }

    @Override
    public <T extends Serializable> List<T> getMapObject(String key, String field) {
        List<T> list = new ArrayList<>();
        HashOperations<String, String, T> o = redisTemplate.opsForHash();
        list.add(o.get(key, field));
        return list;
    }

    @Override
    public boolean setList(String key, List<String> list) {
        Long size = redisTemplate.opsForList().leftPushAll(key, list);
        return size > 0;
    }

    @Override
    public <T extends Serializable> boolean setListObject(String key, List<T> list) {
        Long size = redisTemplate.opsForList().leftPushAll(key, list);
        return size > 0;
    }

    @Override
    public Long sizeOfMap(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public Long sizeOfList(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
