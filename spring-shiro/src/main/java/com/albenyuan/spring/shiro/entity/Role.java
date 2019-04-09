package com.albenyuan.spring.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:36
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @ManyToMany
    List<Permission> permissions;

}

