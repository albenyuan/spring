package com.albenyuan.spring.shiro;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alben Yuan
 * @Date 2019-04-24 09:13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 7257985090091827395L;


    private Integer code;

    private String message;

    private T data;


    public static <T extends Serializable> Result<T> success() {
        return success(null);

    }

    public static <T extends Serializable> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(data);
        return result;

    }

    public static <T extends Serializable> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


}
