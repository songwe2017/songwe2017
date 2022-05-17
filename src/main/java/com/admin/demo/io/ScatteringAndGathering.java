package com.admin.demo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.Stream;

/**
 * @author Songwe
 * @date 2021/9/5 16:01
 */
public class ScatteringAndGathering {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(6666);
        serverSocket.bind(address);
        System.out.println("服务端启动。。。端口：6666");

        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(5);
        buffers[1] = ByteBuffer.allocate(5);

        while (true) {
            SocketChannel socket = serverSocket.accept();
            System.out.println("客户端连接。。。");

            long read;
            while ((read = socket.read(buffers)) != -1) {
                Stream.of(buffers).forEach(System.out::println);
                Stream.of(buffers).forEach(e -> System.out.println(new String(e.array())));
            }

            Stream.of(buffers).forEach(ByteBuffer::flip);

            socket.write(buffers);
        }
    }
}
