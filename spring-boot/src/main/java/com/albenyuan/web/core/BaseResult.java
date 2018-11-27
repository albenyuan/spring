package com.albenyuan.web.core;

import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 11:25
 */
public class BaseResult<T> implements Serializable {

    
    private T data;

    private boolean success;

    private String message;


    public static BaseResult success(Object data) {
        BaseResult result = new BaseResult();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(null);
        return result;
    }

    public static BaseResult error(String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
