package com.admin.demo.io;

import java.nio.IntBuffer;

/**
 * @author Songwe
 * @date 2021/9/3 17:32
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个存放整型 Buffer，存放5个整型
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < 5; i ++) {
            intBuffer.put(i * 2);
        }
        // 读写切换，必须的操作
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
