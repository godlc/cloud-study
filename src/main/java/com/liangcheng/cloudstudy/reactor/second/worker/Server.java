package com.liangcheng.cloudstudy.reactor.second.worker;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 14:12
 */
public class Server {
    public static void main(String[] args) throws IOException {
        Dispatch dispatch = new Dispatch(8, new PongEventHandler());
        Acceptor acceptor = new Acceptor(new InetSocketAddress(8089), dispatch);
        Reactor reactor = new Reactor(acceptor);
        System.out.println("结束了");
    }
}
