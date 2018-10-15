package com.albenyuan.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 15:46
 */
public class WebUtil {

    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String AGENT_ANDROID = "android";
    public static final String AGENT_IPHONE = "iPhone";
    public static final String AGENT_IPAD = "iPad";
    public static final String AGENT_IPOD = "iPod";
    public static final String AGENT_CHROME = "Chrome";
    public static final String AGENT_FIREFOX = "Firefox";
    public static final String AGENT_WECHAT = "MicroMessenger";


    public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    public static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    public static final String IP4_127_0_0_1 = "127.0.0.1";
    public static final String IP6_0_0_0_0_0_0_0_1 = "0:0:0:0:0:0:0:1";
    public static final String IP_UNKNOWN = "unknown";

//    public static final

    public static boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }


    public static ServletContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static ServletContext getContext(HttpServletRequest request) {
        return getContext(request.getSession());
    }

    public static ServletContext getContext(HttpSession session) {
        return session.getServletContext();
    }

    public static Object getAttribute(HttpSession session, String name) {
        return session.getAttribute(name);
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String name) {
        return request.getAttribute(name);
    }

    /**
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String[] headers = {HEADER_X_FORWARDED_FOR, HEADER_PROXY_CLIENT_IP, HEADER_WL_PROXY_CLIENT_IP,};
        String ip;
        for (String ipHeader : headers) {
            ip = request.getHeader(ipHeader);
            if (isHeaderIP(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }


    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader(HEADER_USER_AGENT);
    }

    public static boolean isIos(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return
                isIPhone(userAgent) || isIPad(userAgent) || isIPod(userAgent);
    }

    public static boolean isIPhone(HttpServletRequest request) {
        return isIPhone(getUserAgent(request));
    }

    public static boolean isIPhone(String userAgent) {
        return is(userAgent, AGENT_IPHONE);
    }

    public static boolean isIPod(HttpServletRequest request) {
        return isIPod(getUserAgent(request));
    }

    public static boolean isIPod(String userAgent) {
        return is(userAgent, AGENT_IPOD);
    }

    public static boolean isIPad(HttpServletRequest request) {
        return isIPad(getUserAgent(request));
    }

    public static boolean isIPad(String userAgent) {
        return is(userAgent, AGENT_IPAD);
    }

    public static boolean isAndroid(HttpServletRequest request) {
        return isAndroid(getUserAgent(request));
    }

    public static boolean isAndroid(String userAgent) {
        return is(userAgent, AGENT_ANDROID);
    }

    public static boolean isChrome(HttpServletRequest request) {
        return isChrome(getUserAgent(request));
    }

    public static boolean isChrome(String userAgent) {
        return is(userAgent, AGENT_CHROME);
    }

    public static boolean isFireFox(HttpServletRequest request) {
        return isFireFox(getUserAgent(request));
    }

    public static boolean isFireFox(String userAgent) {
        return is(userAgent, AGENT_FIREFOX);
    }

    public static boolean isWeChat(HttpServletRequest request) {
        return isWeChat(getUserAgent(request));
    }

    public static boolean isWeChat(String userAgent) {
        return is(userAgent, AGENT_WECHAT);
    }

    public static boolean is(HttpServletRequest request, String clientTag) {
        String userAgent = getUserAgent(request);
        return is(userAgent, clientTag);
    }

    public static boolean is(String userAgent, String clientTag) {
        return StringUtils.isNotEmpty(userAgent) && userAgent.contains(clientTag);
    }

    private static boolean isHeaderIP(String ip) {
        return StringUtils.isNotEmpty(ip) && !IP_UNKNOWN.equalsIgnoreCase(ip);
    }
}
