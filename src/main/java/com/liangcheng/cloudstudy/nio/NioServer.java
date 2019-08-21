package com.liangcheng.cloudstudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/16 13:09
 */
public class NioServer {
    public static Selector selector;

    public static void run() throws IOException {
//        ServerSocket server = new ServerSocket(8088);
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(18088));
        channel.configureBlocking(false);
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select() > 0) {
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> keys = set.iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    //删除已经选择的KEY，防止重复提交
                    if (key.isAcceptable()) {
                        handlerAccept(key);
                    }
                    if (key.isReadable()) {
                        handlerRead(key);
                    }
                }
                set.clear();
            }
        }
    }

    public static void handlerAccept(SelectionKey key) throws IOException {

        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel channel1 = channel.accept();
        if (channel1 == null) {
            return;
        }
        channel1.configureBlocking(false);

        channel1.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        System.out.println("与客户端建立连接");
    }

    public static void handlerRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("从客户端读取数据:" + new String(bytes, "UTF-8"));
    }

    public static void handlerWrite(SelectionKey key) {

    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
