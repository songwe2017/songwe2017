package com.admin.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Songwe
 * @date 2021/4/22 20:44
 */
public class MyBlockingQueueForCondition {
    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    public MyBlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {

            while (queue.size() == max) {
                notFull.await();
            }

            queue.add(o);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {

            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "在await之前被唤醒");
                notEmpty.await();
                System.out.println(Thread.currentThread().getName() + "在await之后被唤醒");
            }

            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

}
