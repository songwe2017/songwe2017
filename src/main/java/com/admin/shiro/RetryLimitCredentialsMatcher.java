package com.admin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @author Songwe
 * @since 2022/6/3 1:42
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    
    // todo 集成 redis 并通过 auomaticLong 获取比较次数,但是这也可以通过前端验证
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}
