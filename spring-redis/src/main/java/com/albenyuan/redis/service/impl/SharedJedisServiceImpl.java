package com.albenyuan.redis.service.impl;

import com.albenyuan.core.util.SerializeUtil;
import com.albenyuan.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Alben Yuan
 * @Date 2018-10-19 16:49
 */
public class SharedJedisServiceImpl implements RedisService {
    private Logger logger = LoggerFactory.getLogger(SharedJedisServiceImpl.class);


    @Autowired
    private ShardedJedisPool REDIS_POOL;

    private ShardedJedis open() {
        try {
            return REDIS_POOL.getResource();
        } catch (Exception e) {
            logger.error("get jedis client error:", e);
        }
        return null;
    }

    private void returnResource(ShardedJedis client) {
        REDIS_POOL.returnResource(client);
    }

    @Override
    public boolean isExist(String key) {
        ShardedJedis jedis = open();
        boolean exists = jedis.exists(key);
        returnResource(jedis);
        return exists;
    }

    @Override
    public boolean delete(String key) {
        ShardedJedis jedis = open();
        boolean r = 1 == jedis.del(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean setnx(String key, String value) {
        ShardedJedis jedis = open();
        boolean r = 1 == jedis.setnx(key, value);
        returnResource(jedis);
        return r;
    }

    @Override
    public Long incr(String key) {
        ShardedJedis jedis = open();
        Long r = jedis.incr(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public Long decr(String key) {

        ShardedJedis jedis = open();
        Long r = jedis.decr(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean set(String key, String value) {
        ShardedJedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.set(key, value));
        returnResource(jedis);
        return r;
    }

    @Override
    public boolean set(String key, int seconds, String value) {
        ShardedJedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.setex(key, seconds, value));
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> boolean set(String key, T t) {
        ShardedJedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.set(key.getBytes(), SerializeUtil.serialize(t)));
        returnResource(jedis);
        return r;
    }

    @Override
    public String get(String key) {
        ShardedJedis jedis = open();
        String r = jedis.get(key);
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz) {

        ShardedJedis jedis = open();
        T t = SerializeUtil.deserialize(jedis.get(key.getBytes()), clazz);
        returnResource(jedis);
        return t;
    }

    @Override
    public boolean setMap(String key, Map<String, String> map) {
        ShardedJedis jedis = open();
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
        ShardedJedis jedis = open();
        boolean r = STATUS_OK.equals(jedis.hmset(key, byteMap));
        returnResource(jedis);
        return r;
    }

    @Override
    public List<String> getMap(String key, String field) {
        ShardedJedis jedis = open();
        List<String> r = jedis.hmget(key, field);
        returnResource(jedis);
        return r;
    }

    @Override
    public <T extends Serializable> List<T> getMapObject(String key, String field) {
        ShardedJedis jedis = open();
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
        ShardedJedis jedis = open();
        Long size = jedis.hlen(key);
        returnResource(jedis);
        return size;
    }

    @Override
    public Long sizeOfList(String key) {
        ShardedJedis jedis = open();
        Long size = jedis.llen(key);
        returnResource(jedis);
        return size;
    }
}
