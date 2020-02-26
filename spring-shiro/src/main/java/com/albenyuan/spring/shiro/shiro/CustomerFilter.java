package com.albenyuan.spring.shiro.shiro;

import com.albenyuan.spring.shiro.common.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alben Yuan
 * @Date 2019-04-24 08:35
 */
@Slf4j
public class CustomerFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);

            if (ServletUtils.isAjax(httpRequest)) {
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.sendError(401);
                return false;
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                            "Authentication url [" + getLoginUrl() + "]");
                }
                saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }


}
