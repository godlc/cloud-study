package com.liangcheng.cloudstudy.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/16 11:33
 */
public class NioTest {


    public static void read(String name) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(name);
        FileChannel channel = fileInputStream.getChannel();

        FileInputStream out =
        new FileInputStream(new File("C:\\Users\\城哥\\Pictures\\t1.jpg"));
        FileChannel outChannel = out.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        while (channel.read(bf) != -1){
            bf.flip();
            outChannel.write(bf);
            bf.clear();
        }
        fileInputStream.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        read("C:\\Users\\城哥\\Pictures\\cj0.jpg");
    }
}
