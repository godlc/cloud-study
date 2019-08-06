package com.liangcheng.cloudstudy.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/3 15:52
 */
public class TimeServerHandler implements Runnable {
    private Socket socket = null;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run()  {
        byte[] data = new byte[1024];
        try {
            //循环读取socket传来的数据
            while (true) {
                InputStream in = socket.getInputStream();
                while (in.read(data) != -1) {
                    System.out.println(new String(data, "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }
}
