package com.albenyuan.spring.shiro.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:16
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

}
