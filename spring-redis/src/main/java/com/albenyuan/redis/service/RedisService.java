package com.albenyuan.redis.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author Alben Yuan
 * @Date 2018-10-18 14:24
 */
public interface RedisService {

    String STATUS_OK = "ok";

    boolean isExist(String key);

    boolean delete(String keys);

    boolean setnx(String key, String value);

    Long incr(String key);

    Long decr(String key);

    boolean set(String key, String value);

    <T extends Serializable> boolean set(String key, T t);

    boolean set(String key, int seconds, String value);

    String get(String key);

    <T extends Serializable> T get(String key, Class<T> clazz);

    boolean setMap(String key, Map<String, String> map);

    <T extends Serializable> boolean setMapObject(String key, Map<String, T> map);

    List<String> getMap(String key, String field);

    <T extends Serializable> List<T> getMapObject(String key, String field);

    boolean setList(String key, List<String> list);

    <T extends Serializable> boolean setListObject(String key, List<T> list);

    Long sizeOfMap(String key);

    Long sizeOfList(String key);


}
