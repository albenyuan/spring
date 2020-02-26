package com.albenyuan.spring.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2019-04-09 08:42
 */
@Data
@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 8893009995320120483L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    private String province;

    private String city;

    private String area;

    private String detail;

}
