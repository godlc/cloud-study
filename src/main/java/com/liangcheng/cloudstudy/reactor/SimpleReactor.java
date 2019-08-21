package com.liangcheng.cloudstudy.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/15 14:29
 */
public class SimpleReactor {

    public void run() throws IOException {
        ServerSocket server = new ServerSocket(8088);
        while (true) {
            Socket client = server.accept();
            handler(client);
        }
    }

    public void handler(Socket socket){
        System.out.println(socket.getLocalSocketAddress().toString());}

    public static void main(String[] args) throws IOException {
        SimpleReactor reactor = new SimpleReactor();
        reactor.run();
    }

}
