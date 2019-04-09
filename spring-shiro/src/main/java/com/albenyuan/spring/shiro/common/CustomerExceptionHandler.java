package com.albenyuan.spring.shiro.common;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:42
 */
@Component
public class CustomerExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        view.setAttributesMap(new HashMap<>());
        mv.setView(view);
        return mv;
    }
}
