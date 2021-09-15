package com.auguigu.demo.lock;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Songwe
 * @date 2021/7/22 21:11
 */
@Component
public class LockDemo {

    private int size = 5;

    private final ReentrantLock lock = new ReentrantLock();

    public void decrease() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
            size --;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
            System.out.println(size);
        }
    }
}
