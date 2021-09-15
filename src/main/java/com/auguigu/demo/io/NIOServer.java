package com.auguigu.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Songwe
 * @date 2021/9/6 11:11
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.selectNow() != 0) {
                continue;
            }

            // 这个方法会返回发生了事件的 channel关联的selectionKeys 而非所有
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 这里明确了已经发生了 accept 事件,所以下面不用阻塞
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    System.out.println(socketChannel.getRemoteAddress() + " 上线");
                    socketChannel.configureBlocking(false);

                    // 第三个参数是读取数据后放入的 buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) {

                    SocketChannel socketChannel = null;
                    try {
                        socketChannel = (SocketChannel) selectionKey.channel();
                        // selectionKey.attachment() 方法获取channel 关联的 buffer
                        ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();

                        socketChannel.read(attachment);
                        System.out.println("From Client:" + new String(attachment.array()));

                        // 向其他客户端转发消息
                        for (SelectionKey key : selector.keys()) {
                            Channel channel = key.channel();
                            if (channel instanceof SocketChannel && channel != socketChannel) {
                                SocketChannel otherClient = (SocketChannel) channel;
                                otherClient.write(attachment);
                            }
                        }
                    } catch (IOException e) {
                        try {
                            System.out.println(socketChannel.getRemoteAddress() + " 下线");
                        } catch (IOException e1) {
                            selectionKey.cancel();
                            socketChannel.close();
                        }
                    }
                }

                // 移除 selectionKey， 防止多线程重复操作
                iterator.remove();
            }
        }
    }
}
