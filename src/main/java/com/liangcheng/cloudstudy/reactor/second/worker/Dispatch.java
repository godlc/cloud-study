package com.liangcheng.cloudstudy.reactor.second.worker;

import com.liangcheng.cloudstudy.reactor.second.ThreadFactory.ProcessThreadFactory;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 9:30
 */
public class Dispatch {

    private int workCount;
    private EventHandler eventHandler;
    private List<Process> processes;
    private List<Thread> processThread;
    private AtomicInteger count = new AtomicInteger(0);

    public Dispatch(int workCount, EventHandler eventHandler){
        this.workCount = workCount;
        this.eventHandler = eventHandler;
        processes = new ArrayList<>(workCount);
        processThread = new ArrayList<>(workCount);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dispatchChannel(ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        int index = count.addAndGet(1) % workCount;
        processes.get(index).queueadd(socketChannel);
    }

    void init() throws IOException {
        for (int i = 0; i < workCount; i++) {
            Process process = new Process(eventHandler);
            Thread thread = ProcessThreadFactory.processThreadFactory.newThread(process);
            thread.start();
            processes.add(process);
            processThread.add(thread);
        }
    }
}
