package com.laingcheng.cloudstudy.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author lc
 * @version 1.0
 * @date 2019/7/29 16:16
 */
public class IoTimeServer {
    private ServerSocket server = new ServerSocket(8080);

    public IoTimeServer() throws IOException {
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = server.accept();
                    new Thread(() -> {
                        while (true) {
                            // 给客户端发送当前时间
                            try {
                                socket.getOutputStream().write(new Date().toString().getBytes());
                                socket.getOutputStream().flush();
                            } catch (IOException e) {
                                try {
                                    socket.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new IoTimeServer().start();
    }
}
