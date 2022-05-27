package com.admin.shiro.realm;

import com.admin.entity.User;
import com.admin.service.UserService;
import com.admin.shiro.constant.ShiroConstant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Songwe
 * @date 2022/5/19 15:01
 */
public class DefaultRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginUser = (String) token.getPrincipal();
        LambdaQueryWrapper<User> condition = new LambdaQueryWrapper<User>()
                .eq(User::getName, loginUser);
        User user = userService.getOne(condition);
        if (ObjectUtils.isEmpty(user) || !user.getName().equals(loginUser)) {
            return null; // 返回null 在ModularRealmAuthenticator[184] 会抛出UnknownAccountException
        }
        return new SimpleAuthenticationInfo(loginUser, user, getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroConstant.HASH_ALGORITHM);
        matcher.setHashIterations(ShiroConstant.HASH_ITERATIONS);
        super.setCredentialsMatcher(matcher);
    }
    
}
