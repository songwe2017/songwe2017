package com.admin.controller;

import com.admin.common.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Songwe
 * @date 2021/2/1 15:43
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    
    @ResponseBody
    @PostMapping("/login")
    public Result login(String username, String password) {
        
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }
        catch (UnknownAccountException e) {
            return Result.failed("用户名错误");
        }
        catch (IncorrectCredentialsException e) {
            return Result.failed("密码错误");
        }
        catch (LockedAccountException e) {
            return Result.failed("账号已被锁定,请联系管理员");
        }
        catch (AuthenticationException e) {
            return Result.failed("账户验证失败");
        }
        return Result.success();
    }
    
}
