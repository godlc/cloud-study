package com.liangcheng.cloudstudy.reactor.first;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/21 18:04
 */
public class HandleThread implements Runnable {
    private TranferDto dto;

    public HandleThread(TranferDto dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        ByteBuffer bf = dto.getByteBuffer();
        byte[] bytes = new byte[bf.limit()];
        bf.get(bytes);
        System.out.println("处理客户端的数据了" + new String(bytes));
        bf = ByteBuffer.allocate(1024);
        bf.put("收到你的信息了".getBytes());
        try {
            dto.getSocketChannel().write(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
