package com.albenyuan.spring.batch.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2019-04-21 21:57
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -4573696307365490629L;


    private String firstName;

    private String lastName;

}
