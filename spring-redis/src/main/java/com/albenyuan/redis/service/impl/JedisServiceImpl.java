package com.albenyuan.redis.service.impl;

import com.albenyuan.core.util.SerializeUtil;
import com.albenyuan.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Alben Yuan
 * @Date 2018-10-18 14:25
 */
@Service("jedisService")
public class JedisServiceImpl implements RedisService {

    private Logger logger = LoggerFactory.getLogger(JedisServiceImpl.class);


    @Autowired
    private JedisPool JEDIS_POOL;

    private Jedis open() {
        try {
            return JEDIS_POOL.getResource();
        } catch (Exception e) {
            logger.error("get jedis client error:", e);
        }
        return null;
    }

    private void returnResource(Jedis client) {
        JEDIS_POOL.returnResource(client);
    }

    @Override
    public boolean isExist(String key) {
        Jedis jedis = open();
        boolean exists = jedis.exists(key);
        returnResource(jedis);
        return exists;
    }

    @Override
    public boolean delete(String key) {
        Jedis jedis = open();
        boolean r = 1 == jedis.del(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean setnx(String key, String value) {
        Jedis jedis = open();
        boolean r = 1 == jedis.setnx(key, value);
        returnResource(jedis);
        return r;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = open();
        Long r = jedis.incr(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public Long decr(String key) {

        Jedis jedis = open();
        Long r = jedis.decr(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean set(String key, String value) {
        Jedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.set(key, value));
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean set(String key, int seconds, String value) {
        Jedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.setex(key, seconds, value));
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> boolean set(String key, T t) {
        Jedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.set(key.getBytes(), SerializeUtil.serialize(t)));
        returnResource(jedis);
        return r;
    }

    @Override
    public String get(String key) {
        Jedis jedis = open();
        String r = jedis.get(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz) {

        Jedis jedis = open();
        T t = SerializeUtil.deserialize(jedis.get(key.getBytes()), clazz);
        returnResource(jedis);
        return t;
    }

    @Override
    public boolean setMap(String key, Map<String, String> map) {
        Jedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.hmset(key, map));
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> boolean setMapObject(String key, Map<String, T> map) {
        Map byteMap = new HashMap<>();
        for (String mapKey : map.keySet()) {
            byteMap.put(mapKey.getBytes(), SerializeUtil.serialize(map.get(mapKey)));
        }
        Jedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.hmset(key, byteMap));
        returnResource(jedis);
        return r;
    }

    @Override
    public List<String> getMap(String key, String field) {
        Jedis jedis = open();
        List<String> r = jedis.hmget(key, field);
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> List<T> getMapObject(String key, String field) {
        Jedis jedis = open();
        List<byte[]> byteList = jedis.hmget(key.getBytes(), field.getBytes());
        List<T> list = new ArrayList<>();
        for (byte[] bytes : byteList) {
            list.add((T) SerializeUtil.deserialize(bytes));
        }
        returnResource(jedis);
        return list;
    }

    @Override
    public boolean setList(String key, List<String> list) {
        return STATUS_OK.equals(null);
    }

    @Override
    public <T extends Serializable> boolean setListObject(String key, List<T> list) {
        return STATUS_OK.equals(null);
    }

    @Override
    public Long sizeOfMap(String key) {
        Jedis jedis = open();
        Long size = jedis.hlen(key);
        returnResource(jedis);
        return size;
    }

    @Override
    public Long sizeOfList(String key) {
        Jedis jedis = open();
        Long size = jedis.llen(key);
        returnResource(jedis);
        return size;
    }
}
