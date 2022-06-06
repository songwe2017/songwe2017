package com.admin.shiro;

import com.admin.common.constant.ShiroConstant;
import com.admin.model.User;
import com.admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
    //private CacheManager cacheManager;
    //
    //@Override
    //public void setCacheManager(CacheManager cacheManager) {
    //    this.cacheManager = cacheManager;
    //}

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginUser = (String) principals.getPrimaryPrincipal();
        //Cache<String, Object> cache = cacheManager.getCache(loginUser);
        
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginUser = (String) token.getPrincipal();
        LambdaQueryWrapper<User> condition = new LambdaQueryWrapper<User>()
                .eq(User::getName, loginUser);

        User user = userService.getOne(condition);
        
        if (user == null) {
            return null; // 返回null 在ModularRealmAuthenticator[184] 会抛出UnknownAccountException
        }

        return new SimpleAuthenticationInfo(loginUser, user.getPassword(), ShiroByteSource.Util.bytes(user.getSalt()), getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroConstant.HASH_ALGORITHM);
        matcher.setHashIterations(ShiroConstant.HASH_ITERATIONS);
        super.setCredentialsMatcher(matcher);
    }
    
}
