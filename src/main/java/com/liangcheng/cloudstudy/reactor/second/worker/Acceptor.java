package com.liangcheng.cloudstudy.reactor.second.worker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * reactor 模式的第二种
 *
 * @author lc
 * @version 1.0
 * @date 2019/8/15 14:58
 */
public class Acceptor implements LifeCycle, Runnable{
    private Status status = Status.unstart;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Dispatch dispatch;
//    private InetSocketAddress inetSocketAddress;

    public Acceptor(InetSocketAddress inetSocketAddress, Dispatch dispatch) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        this.dispatch = dispatch;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }


    @Override
    public void run() {
        while (true){
            try {
                final int count = selector.select();
                if (count > 0){
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();

                        dispatch.dispatchChannel((ServerSocketChannel) key.channel());
                    }
                    keys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
