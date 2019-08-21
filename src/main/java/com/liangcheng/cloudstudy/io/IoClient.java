package com.liangcheng.cloudstudy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author lc
 * @version 1.0
 * @date 2019/7/29 16:08
 */
public class IoClient {

    public IoClient() throws IOException {
    }

    public void connetct() throws IOException {
        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8088);
        socket.connect(address);
//        while (true){
//            try {
//                socket.getOutputStream().write((new Date() + ": hello world").getBytes());
//                socket.getOutputStream().flush();
//                Thread.sleep(2000);
//
//                InputStream in = socket.getInputStream();
//                byte[] data = new byte[1024];
//                while(in.read(data) != -1){
//                    System.out.println(new String(data, "UTF-8"));
//                }
//            } catch (Exception e) {
//            }
//        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            new IoClient().connetct();
        }
    }
}
