package com.liangcheng.cloudstudy.reactor.first;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 处理连接的线程
 * @author lc
 * @version 1.0
 * @date 2019/8/21 16:40
 */
public class Reactor implements Runnable {

    private Selector selector;
    private Dispatch dispatch;

    public Reactor(Dispatch dispatch){
        this.dispatch = dispatch;
    }

    public void init(InetSocketAddress address) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                int cont = selector.select();
                if (cont > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()){
                            acceptHandle(key);
                        }
                        if (key.isReadable()){
                            readHandle(key);
                        }
                    }
                    keys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandle(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("建立连接完成" + socketChannel.getRemoteAddress().toString());
    }

    private void readHandle(SelectionKey key) throws IOException {

    }
}
