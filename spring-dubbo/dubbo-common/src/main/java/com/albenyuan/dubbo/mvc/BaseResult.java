package com.albenyuan.dubbo.mvc;

import com.sun.xml.internal.rngom.parse.host.Base;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 14:38
 */
public class BaseResult<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = -1;

    private String message;

    private T data;

    private Integer code;

    private BaseResult() {
    }

    public static BaseResult success() {
        return success(new Object());
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

}
