package com.auguigu.demo;

import com.auguigu.demo.lock.LockDemo;
import com.auguigu.demo.model.User;
import com.auguigu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private LockDemo lockDemo;

    @Test
    public void test() {
        User user = userService.getUser(1L);
        System.out.println(user);
    }

    @Test
    public void testLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6; i ++) {
            executorService.execute(() -> {
                lockDemo.decrease();
            });
        }
        executorService.shutdown();
        if (!executorService.isTerminated()) {
            Thread.currentThread().join();
        }
    }
}
