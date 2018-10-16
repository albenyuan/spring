package com.albenyuan.dubbo.handler;

import com.albenyuan.dubbo.mvc.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 16:55
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object handle(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Accept:{}", request.getHeader("Accept"));
        logger.debug("Content-Type:{}", request.getHeader("Content-Type"));
        logger.debug("Content-Type:{}", response.getHeader("Content-Type"));
        logger.warn("【系统异常】", e);
        return BaseResult.failure("系统异常");
    }
}
