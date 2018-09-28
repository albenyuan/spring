package com.albenyuan.mybatis.model;

import com.albenyuan.common.data.model.BaseModel;

/**
 * @Author Alben Yuan
 * @Date 2018-09-28 17:17
 */
public class User extends BaseModel {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
