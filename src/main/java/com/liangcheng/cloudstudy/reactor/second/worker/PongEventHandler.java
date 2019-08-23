package com.liangcheng.cloudstudy.reactor.second.worker;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 14:16
 */
public class PongEventHandler implements EventHandler {
    private final ByteBuffer buffer = ByteBuffer.wrap("Pong...".getBytes(StandardCharsets.UTF_8));

    private AtomicInteger integer = new AtomicInteger(0);
    @Override
    public void connect(final SessionContext session) throws IOException {
//        session.interestOp(SelectionKey.OP_WRITE);
        System.out.println("连接上了" + ((SocketChannel)session.getSelectionKey().channel()).getRemoteAddress().toString());


    }

    @Override
    public void read(final SessionContext session) {

    }

    @Override
    public void write(final SessionContext session) {
        try {
            ByteBuffer b = buffer.duplicate();
            ((SocketChannel)(session.getSelectionKey().channel())).write(b);
            System.out.println(integer.addAndGet(1));
            if (b.hasRemaining()) {
                b.compact();
            } else {
//                session.close();
            }
        } catch (final IOException ex) {
        }
    }

//    @Override
//    public void disconnect(final SessionContext session) {
//        System.out.println("Closed: " + session.remoteAddress());
//    }
}
