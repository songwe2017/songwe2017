package com.admin.controller;

import com.admin.common.util.R;
import com.admin.common.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
        catch (LockedAccountException e) {
            return R.failed().msg("账号已被锁定,请联系管理员");
        }
        catch (Exception e) {
            return R.failed().msg("账户验证失败");
        }
        return R.success();
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
