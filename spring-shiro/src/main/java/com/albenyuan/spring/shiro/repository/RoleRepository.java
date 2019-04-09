package com.albenyuan.spring.shiro.repository;

import com.albenyuan.spring.shiro.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:00
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {


    @Query("select r from Role  where  r.id in (select  ur.roleId from com.albenyuan.spring.shiro.entity.UserRole ur where  ur.userId = ?1)")
    Stream<Role> findRoleByUserId(Long userId);


}
