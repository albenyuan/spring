package com.albenyuan.core.data.model;

import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2018-09-28 17:18
 */
public class BaseModel implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
