package com.liangcheng.cloudstudy.reactor.first;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 传输的实体
 * @author lc
 * @version 1.0
 * @date 2019/8/21 17:29
 */
public class TranferDto {
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;

    public TranferDto(SocketChannel socketChannel, ByteBuffer byteBuffer) {
        this.socketChannel = socketChannel;
        this.byteBuffer = byteBuffer;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public void setByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }
}
