package com.albenyuan.dubbo.mvc;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 14:38
 */
public class BaseResult<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = -1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer code;


    public static BaseResult success() {
        return success(new HashMap<>());
    }

    public static BaseResult success(Object object) {
        BaseResult result = new BaseResult();
        result.data = object;
        result.code = SUCCESS;
        return result;
    }


    public static BaseResult failure(Integer code) {
        BaseResult result = new BaseResult();
        result.code = code;
        return result;
    }

    public static BaseResult failure(Integer code, String message) {
        BaseResult result = new BaseResult();
        result.code = code;
        result.message = message;
        return result;
    }

    public static BaseResult failure(String message) {
        return failure(ERROR, message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
