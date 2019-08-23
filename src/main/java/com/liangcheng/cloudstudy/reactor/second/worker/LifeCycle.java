package com.liangcheng.cloudstudy.reactor.second.worker;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 9:31
 */
public interface LifeCycle {

    /**
     * 存活状态
     */
    enum Status{
       unstart, active, stop;
    }

    Status getStatus();
}
