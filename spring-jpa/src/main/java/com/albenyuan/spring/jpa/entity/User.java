package com.albenyuan.spring.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2019-04-09 08:29
 */
@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 5294552194128749595L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;


}
