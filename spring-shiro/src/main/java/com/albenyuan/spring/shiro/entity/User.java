package com.albenyuan.spring.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:36
 */
@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -3536068082086569052L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String salt;

}
