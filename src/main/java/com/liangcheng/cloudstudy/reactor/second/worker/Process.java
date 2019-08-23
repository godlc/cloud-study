package com.liangcheng.cloudstudy.reactor.second.worker;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 9:30
 */
public class Process implements Runnable {

    private Selector selector;
    private EventHandler eventHandler;
    private Queue<SocketChannel> newChannels;
    private Set<SessionContext> sessions;
    private Set<SessionContext> closeSessions;

    private volatile LifeCycle.Status status = LifeCycle.Status.active;

    public Process(EventHandler eventHandler) throws IOException {
        selector = Selector.open();
        this.eventHandler = eventHandler;
        newChannels = new ArrayDeque<>();
        sessions = new HashSet<>();
        closeSessions = new HashSet<>();
    }

    public void queueadd(SocketChannel socketChannel) throws IOException {
        newChannels.add(socketChannel);
        selector.wakeup();
    }

    private void processnewChannels() throws IOException {
        for (SocketChannel channel : newChannels){
            channel.configureBlocking(false);
            SelectionKey key = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            SessionContext sessionContext = new SessionContext(key, this, new HashMap(8));
            key.attach(key);
            sessions.add(sessionContext);
            eventHandler.connect(sessionContext);
        }
        newChannels.clear();
    }

    private void processEvents(SessionContext sessionContext) throws IOException {
        SelectionKey key = sessionContext.getSelectionKey();

        if (key.isReadable()){
            eventHandler.read(sessionContext);
        }
        if (key.isWritable()){
            eventHandler.write(sessionContext);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                final int count = selector.select();
                if (count > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        SessionContext sessionContext = new SessionContext(key, this, new HashMap(4));
                        sessions.add(sessionContext);
                        processEvents(sessionContext);
                    }
                    keys.clear();
                    processCloseSessions();
                }
                processnewChannels();
            }
            //关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processCloseSessions() throws IOException {
        for (SessionContext context : closeSessions){
            context.getSelectionKey().channel().close();
        }
        closeSessions.clear();
    }
}
