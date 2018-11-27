package com.albenyuan.springboot.handler;

import com.albenyuan.web.core.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 12:47
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @Value("${env.production}")
    private Boolean isProduction;

    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Object exception(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        if (!isProduction) {
            throw e;
        }
        logger.info("request:{}", request.getContentType());
        logger.info("response:{}", response.getContentType());
        logger.info("exception:", e);
        if (isAjax(request)) {
            return BaseResult.error(e.getMessage());
        } else {
            ModelAndView mv = new ModelAndView();
            mv.addObject("exception", e);
            mv.addObject("url", request.getRequestURI());
            mv.setViewName("error");
            return mv;
        }

    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
