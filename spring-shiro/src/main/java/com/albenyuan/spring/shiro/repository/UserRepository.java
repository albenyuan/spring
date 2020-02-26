package com.albenyuan.spring.shiro.repository;

import com.albenyuan.spring.shiro.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:58
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    User findFirstByUsername(String username);

}
