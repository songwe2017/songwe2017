package com.auguigu.demo.thread;

/**
 * @author Songwe
 * @date 2021/4/22 21:04
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueueForCondition myBlockingQueueForCondition = new MyBlockingQueueForCondition(16);

        new Thread(() -> {
            try {
                myBlockingQueueForCondition.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread3").start();

        new Thread(() -> {
            try {
                myBlockingQueueForCondition.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread4").start();

        Thread.sleep(1000);

        new Thread(() -> {
            try {
                myBlockingQueueForCondition.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1").start();

        new Thread(() -> {
            try {
                myBlockingQueueForCondition.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }
}
