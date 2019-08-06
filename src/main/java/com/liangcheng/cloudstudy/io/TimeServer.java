package com.liangcheng.cloudstudy.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 伪IO的服务器
 * 采用线程池实现创建连接的线程
 * @author lc
 * @version 1.0
 * @date 2019/8/3 15:47
 */
public class TimeServer {
    private ServerSocket server = new ServerSocket(8080);

    public TimeServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        TimeServer timeServer = new TimeServer();
        timeServer.start();
    }
    public void start(){
        TimeServerHandlerExecutor executor = new TimeServerHandlerExecutor();
        new Thread(()->{
            while (true) {
                try {
                    Socket socket = server.accept();
                    executor.executor(new TimeServerHandler(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        }).start();
    }

    class TimeServerHandlerExecutor{
        private ThreadPoolExecutor executor;
        public TimeServerHandlerExecutor(){
            executor = new ThreadPoolExecutor(1, 2, 120L,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        }

        public void executor(Runnable task){
            executor.execute(task);
        }
    }
}
