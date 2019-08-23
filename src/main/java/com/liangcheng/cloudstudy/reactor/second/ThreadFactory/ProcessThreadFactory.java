package com.liangcheng.cloudstudy.reactor.second.ThreadFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 9:26
 */
public class ProcessThreadFactory implements ThreadFactory {

    public static ProcessThreadFactory processThreadFactory = new ProcessThreadFactory();

    private AtomicInteger num = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"process-" + num.addAndGet(1) + "-Thread");
    }
}
