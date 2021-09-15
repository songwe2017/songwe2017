package com.auguigu.demo.io;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Songwe
 * @date 2021/9/4 21:53
 */
public class NioFileChannel {
    public static void main(String[] args) throws Exception {
        String str = "hello, 尚硅谷";

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Songwe\\file.txt");
        FileChannel channel = fileOutputStream.getChannel();
        channel.write(buffer);

        fileOutputStream.close();
    }
}
