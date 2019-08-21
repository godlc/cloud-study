package com.liangcheng.cloudstudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/16 13:23
 */
public class NioClient {
    public static void run() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(18088));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        while (true){
            long time =System.currentTimeMillis();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(("现在的时间是:" + new Date().toString()).getBytes("UTF-8"));
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            Thread.sleep(2000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient.run();
    }
}
