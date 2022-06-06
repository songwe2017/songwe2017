package com.admin.shiro.filter;

import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 在线并发人数控制
 *  1、只针对登录用户控制，判断是否登录
 *  2、用 redis 创建队列
 *  3、判断当前 sessionId 是否存在此用户队列
 *  4、不存在则将 sessionId 放入队列尾端
 *  5、存在则判断当前队列大小是否超过限定登录人数
 *  6、超过：从队列头获取 sessionId，从 sessionManager 取出 session，从 sessionDao 移除该 session
 *  7、未超过：放过
 * @author Songwe
 * @since 2022/6/3 2:02
 */
//@Component 
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class KickOutAccessControlFilter extends AccessControlFilter {
    
    // todo 集成 reids
    
    private final SessionDAO sessionDAO;
    
    private final SessionManager sessionManager;
    
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            return true;
        }

        return false;
    }
}
