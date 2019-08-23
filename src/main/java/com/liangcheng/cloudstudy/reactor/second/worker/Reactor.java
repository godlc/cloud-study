package com.liangcheng.cloudstudy.reactor.second.worker;

import com.liangcheng.cloudstudy.reactor.second.ThreadFactory.AcceaptThreadFactory;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/22 17:43
 */
public class Reactor {
    private Acceptor acceapt;
    private Thread acceaptThread;

    public Reactor(Acceptor acceapt) {
        this.acceapt = acceapt;
        this.acceaptThread = AcceaptThreadFactory.acceaptThreadFactory.newThread(acceapt);
        acceaptThread.start();
    }

    public Acceptor getAcceapt() {
        return acceapt;
    }

    public Thread getAcceaptThread() {
        return acceaptThread;
    }
}
