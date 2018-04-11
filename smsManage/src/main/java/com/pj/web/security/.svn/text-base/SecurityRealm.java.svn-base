package com.pj.web.security;

import com.pj.core.util.PasswordHash;
import com.pj.web.model.Permission;
import com.pj.web.model.Role;
import com.pj.web.model.User;
import com.pj.web.service.PermissionService;
import com.pj.web.service.RoleService;
import com.pj.web.service.UserService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        final User user = userService.selectByUsername(username);
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getUserId());
        for (Role role : roleInfos) {
            // 添加角色
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                // 添加权限
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	String message = "用户名或密码错误";
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        
        final User user = userService.selectByUsername(username);
        if (user == null) {
        	throw new AuthenticationException(message);
        } else {
        	try {
				if (!PasswordHash.validatePassword(password, user.getPassword())) {
					throw new AuthenticationException(message);
				}
			} catch (Exception e) {
				throw new AuthenticationException(message);
			}
        }
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
