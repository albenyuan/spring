package com.albenyuan.dubbo.data.model;

import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 10:45
 */
public class User implements Serializable {

    private String username;

    private String password;

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
}
