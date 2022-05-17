package com.admin.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author Songwe
 * @date 2021/9/6 14:24
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress(6666);

        if (!socketChannel.connect(address)) {
            // 如果 connect 方法连接失败，要通过改方法完成连接操作
            while (!socketChannel.finishConnect()) {
                System.out.println("未连接，可做其他工作");
            }
        }

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            // 这个方法相当于先 allocate，在 put
            ByteBuffer byteBuffer = ByteBuffer.wrap(scanner.nextLine().getBytes());

            socketChannel.write(byteBuffer);
        }
    }
}
