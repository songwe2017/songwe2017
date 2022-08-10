package com.admin.controller;

import com.admin.common.base.R;
import com.admin.shiro.JwtTokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Songwe
 * @date 2021/2/1 15:43
 */
@RestController
@RequestMapping("/admin")
public class LoginController {
    
    @PostMapping("/login")
    public R login(@RequestParam("username") String username,
                   @RequestParam("password") String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }
        catch (UnknownAccountException e) {
            return R.failed().msg("用户名错误");
        }
        catch (IncorrectCredentialsException e) {
            return R.failed().msg("密码错误");
        }
        catch (Exception e) {
            return R.failed().msg("账户验证失败");
        }
        String jwtToken = JwtTokenManager.issueToken(username, 30*60*1000L, subject.getSession().getId().toString(), null);
        return R.success(jwtToken);
    }
    
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }
    
    @GetMapping("/logout")
    public R logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.success();
    }
    
}
