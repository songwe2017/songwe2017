//package com.admin.demo.shiro.realm;
//
//import com.admin.demo.entity.User;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @author Songwe
// * @date 2022/5/19 15:01
// */
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class DefaultRealm extends AuthorizingRealm {
//    
//    private final UserService userService;
//    
//    // 授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        return null;
//    }
//
//    // 认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String loginUser = (String) token.getPrincipal();
//        LambdaQueryWrapper<User> condition = new LambdaQueryWrapper<User>()
//                .eq(User::getUsername, loginUser);
//        User user = userService.getOne(condition);
//        if (ObjectUtils.isEmpty(user)) {
//            throw new UnknownAccountException("用户不存在！");
//        }
//        return new SimpleAuthenticationInfo(loginUser, user, getName());
//    }
//}
