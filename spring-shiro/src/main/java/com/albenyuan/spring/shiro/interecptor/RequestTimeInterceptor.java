package com.albenyuan.spring.shiro.interecptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 21:51
 */
@Slf4j
@Component
public class RequestTimeInterceptor implements HandlerInterceptor {
    public static final String start_time = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(start_time, System.currentTimeMillis());
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object time = request.getAttribute(start_time);
        if (time instanceof Long) {
            log.info("request time: {}ms", System.currentTimeMillis() - (Long) time);
        } else {
            log.warn("未统计到请求时长", request.getRequestURL());
        }
    }
}
