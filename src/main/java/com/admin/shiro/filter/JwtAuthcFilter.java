package com.admin.shiro.filter;

import com.admin.common.constant.ShiroConstant;
import com.admin.common.util.JsonUtils;
import com.admin.common.util.R;
import com.admin.shiro.JwtTokenManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Songwe
 * @since 2022/6/3 22:22
 */
//@Component
public class JwtAuthcFilter extends FormAuthenticationFilter {
    
    private final JwtTokenManager jwtTokenManager;

    public JwtAuthcFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    // 通过校验
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(ShiroConstant.TOKEN);

        if (StringUtils.isNotEmpty(token)) {
            boolean verifyToken = jwtTokenManager.verifyToken(token);
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
            response.getWriter().write(JsonUtils.toJson(request));
        }

        return super.onAccessDenied(request, response);
    }
}
