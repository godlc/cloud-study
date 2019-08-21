package com.liangcheng.cloudstudy.reactor.first;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/21 16:40
 */
public class Dispatch {

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(16);


    public void handler(Runnable r) {
        executor.execute(r);
    }
}
