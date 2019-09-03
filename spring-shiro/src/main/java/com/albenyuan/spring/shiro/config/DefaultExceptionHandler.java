package com.albenyuan.spring.shiro.config;

import com.albenyuan.spring.shiro.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alben Yuan
 * @Date 2019-04-24 14:31
 */
@Slf4j
@RestController
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handler(Throwable t) {
        log.warn("error: {}", t.getClass().getName());
        return Result.failure(500, t.getMessage());
    }
}
