package com.albenyuan.spring.shiro.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Alben Yuan
 * @Date 2019-04-24 08:43
 */
public class ServletUtils {

    public static final String HEADER_X_REQUESTED_WITH = "X-Requested-With";
    public static final String HEADER_XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * 判断ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return HEADER_XML_HTTP_REQUEST.equals(request.getHeader(HEADER_X_REQUESTED_WITH));
    }


}
