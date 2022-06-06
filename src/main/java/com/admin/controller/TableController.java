package com.admin.controller;

import com.admin.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Songwe
 * @date 2022/5/28 13:49
 */
@Controller
@RequestMapping("table")
public class TableController {
    
    @GetMapping("basic_table")
    public String basic_table() {
        return "/table/basic_table";
    }

    @GetMapping("dynamic_table")
    public String dynamic_table(Map<String, Object> map) {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        user.setPassword("123456");
        user.setPhone("152");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("李四");
        user2.setPassword("123456");
        user2.setPhone("138");

        User user3 = new User();
        user3.setId(3L);
        user3.setName("王五");
        user3.setPassword("123456");
        user3.setPhone("138");

        List<User> users = Arrays.asList(user, user2, user3);
        map.put("users", users);

        return "/table/dynamic_table";
    }

    @GetMapping("editable_tabl")
    public String editable_tabl() {
        return "/table/editable_tabl";
    }

    @GetMapping("pricing_table")
    public String pricing_table() {
        return "/table/pricing_table";
    }

    @GetMapping("responsive_table")
    public String responsive_table() {
        return "/table/responsive_table";
    }
}
