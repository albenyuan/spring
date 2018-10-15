package com.albenyuan.dubbo.interceptor;

import com.albenyuan.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 15:40
 */
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    private static final String REQUEST_START_TIME = "requestStartTime";
    private static final String REQUEST_STOP_TIME = "requestStopTime";

    private static Logger logger = LoggerFactory.getLogger(RequestTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        long endTime = System.currentTimeMillis();
        logger.info("[{}] [{} ms] [{}]", WebUtil.getIP(request), endTime - startTime, request.getRequestURI());
        super.afterCompletion(request, response, handler, ex);
    }
}
