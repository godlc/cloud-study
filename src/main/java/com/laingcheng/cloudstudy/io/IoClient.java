package com.laingcheng.cloudstudy.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

/**
 * @author lc
 * @version 1.0
 * @date 2019/7/29 16:08
 */
public class IoClient {
    private Socket socket = new Socket();

    public IoClient() throws IOException {
    }

    public void connetct() throws IOException {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        socket.connect(address);
        while (true){
            try {
                socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                socket.getOutputStream().flush();
                Thread.sleep(2000);

                InputStream in = socket.getInputStream();
                byte[] data = new byte[1024];
                while(in.read(data) != -1){
                    System.out.println(new String(data, "UTF-8"));
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new IoClient().connetct();
    }
}
