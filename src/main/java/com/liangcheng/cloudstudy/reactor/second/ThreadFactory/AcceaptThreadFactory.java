package com.liangcheng.cloudstudy.reactor.second.ThreadFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 9:26
 */
public class AcceaptThreadFactory implements ThreadFactory {
    public static AcceaptThreadFactory acceaptThreadFactory = new AcceaptThreadFactory();
    private AtomicInteger num = new AtomicInteger(0);
    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"Acceapt-" + num.addAndGet(1) + "-Thread");
    }
}
