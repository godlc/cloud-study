package com.liangcheng.cloudstudy.reactor.second.worker;

import java.io.IOException;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/23 10:11
 */
public interface EventHandler {

    void connect(SessionContext sessionContext) throws IOException;

    void read(SessionContext sessionContext);

    void write(SessionContext sessionContext);
}
