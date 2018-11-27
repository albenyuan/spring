package com.albenyuan.springboot.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @Author Alben Yuan
 * @Date 2018-11-15 14:42
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
