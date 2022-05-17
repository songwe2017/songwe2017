package com.admin.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本类用来演示 BIO 的阻塞效果，充当 Socket 服务端
 * 客户端使用 Telnet localhost 6666 启动
 * 启动后输入 'CTRL+] 出现 Microsoft Telnet>
 * 输入 send hello 即可向服务端发送 hello
 * @author Songwe
 * @date 2021/9/3 16:10
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();

        ServerSocket socket = new ServerSocket(6666);
        System.out.println("服务器启动。。。");

        while (true) {
            System.out.println("等待连接。。。");
            final Socket accept = socket.accept();
            System.out.println("连接到客户端");

            service.execute(() -> {
                handle(accept);
            });
        }
    }

    public static void handle(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            while (true) {
                int read = inputStream.read(bytes);

                if (read != -1) {
                    System.out.println(new String(bytes));
                    System.out.println("线程： " + Thread.currentThread().getName());
                }
                else {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
