package com.liangcheng.cloudstudy.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * IO server采用的是一个请求一个响应
 * @author lc
 * @version 1.0
 * @date 2019/7/29 15:44
 */
public class IoServer {
    private ServerSocket server = new ServerSocket(8080);

    public IoServer() throws IOException {
    }

    public void start(){
        new Thread(()->{
            while (true){
                try {
                    Socket socket = server.accept();
                    new Thread(() -> {
                        while (true) {
                            byte[] data = new byte[1024];
                            try {
                                InputStream in = socket.getInputStream();
                                while(in.read(data) != -1){
                                    System.out.println(new String(data, "UTF-8"));
                                }
                            } catch (IOException e) {
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
        new IoServer().start();
    }
}
