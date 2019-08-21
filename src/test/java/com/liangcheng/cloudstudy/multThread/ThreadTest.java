package com.liangcheng.cloudstudy.multThread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/15 11:47
 */
public class ThreadTest {

    @Test
    public void test(){
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        queue.add(2);
    }
}
