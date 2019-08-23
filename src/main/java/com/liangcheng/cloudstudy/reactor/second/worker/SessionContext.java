package com.liangcheng.cloudstudy.reactor.second.worker;

import java.nio.channels.SelectionKey;
import java.util.Map;

/**
 * 绑定selectionKey的对象
 * @author lc
 * @version 1.0
 * @date 2019/8/23 10:11
 */
public class SessionContext {

    private SelectionKey selectionKey;
    private Process process;
    private Map map;

    public SessionContext(SelectionKey selectionKey, Process process, Map map) {
        this.selectionKey = selectionKey;
        this.process = process;
        this.map = map;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
