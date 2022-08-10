package com.admin.shiro.filter;

import com.admin.common.base.R;
import com.admin.common.constant.ShiroConstant;
import com.admin.shiro.JwtTokenManager;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Songwe
 * @since 2022/6/3 22:22
 */
@Slf4j
@Component
public class JwtAuthcFilter extends FormAuthenticationFilter {
    
    // 通过校验
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(ShiroConstant.TOKEN);

        if (StringUtils.isNotEmpty(token)) {
            boolean verifyToken = false; 
            try {
                verifyToken = JwtTokenManager.verifyToken(token);
            } 
            catch (JWTVerificationException e) {
                log.error("Jwt token 验证失败");
                return false;
            }
            if (verifyToken) {
                return super.isAccessAllowed(request, response, mappedValue);
            }
            else {
                return false;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    // 校验失败
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(ShiroConstant.TOKEN);

        if (StringUtils.isNotEmpty(token)) {
            R result = R.failed().msg("token 校验失败");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            // JDK1.7之后支持try语句（）里面的资源必须实现Closeble接口。程序会在try执行之后默认去关闭（）里面的资源。
            try (PrintWriter writer = response.getWriter()){
                writer.write(result.toString());
            } 
        }
        return false;
    }
}
