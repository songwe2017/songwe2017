package com.auguigu.demo.thread;

import com.auguigu.demo.model.User;

/**
 * @author Songwe
 * @date 2022/4/26 21:14
 */
public class ThreadLocalHolder {
    private final static ThreadLocal<User> holder = new ThreadLocal<>();
    
    public static void set(User user) {
        holder.set(user);
    }
    
    public static User get() {
        return holder.get();
    }
    
    // 使用完删除避免内存泄露
    public static void remove() {
        holder.remove();
    }
    
}
