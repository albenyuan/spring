package com.albenyuan.spring.shiro.shiro;

import com.albenyuan.spring.shiro.entity.Permission;
import com.albenyuan.spring.shiro.entity.User;
import com.albenyuan.spring.shiro.service.AccountService;
import com.albenyuan.spring.shiro.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Alben Yuan
 * @Date 2019-04-06 22:03
 */
@Slf4j
public class CustomerShiroRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("doGetAuthorizationInfo");
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = accountService.findByUsername(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        roleService.findByUserId(user.getId()).forEach(role -> {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        });

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求

        Object principal = token.getPrincipal();
        log.debug("doGetAuthenticationInfo: {}",principal);
        if (null == principal) {
            return null;
        }
        //获取用户信息
        String username = principal.toString();
        User user = accountService.findByUsername(username);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        }
    }
}
