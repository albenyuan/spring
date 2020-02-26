package com.albenyuan.spring.jpa.repository;

import com.albenyuan.spring.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Alben Yuan
 * @Date 2019-03-13 14:23
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
