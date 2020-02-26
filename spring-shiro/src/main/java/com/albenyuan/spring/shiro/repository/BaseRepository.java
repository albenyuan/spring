package com.albenyuan.spring.shiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:59
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
}
