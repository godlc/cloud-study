package com.liangcheng.cloudstudy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/8 14:05
 */
public class TimeServer {

    /**
     * 实际上是定义了两个线程组
     */
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() throws InterruptedException {
        //netty 的辅助启动类
        ServerBootstrap server = new ServerBootstrap();
        //绑定了一个父工作组和子工作组
        server.group(boosGroup, workerGroup)
                //绑定了一个Nio服务端channel通道
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
//                .handler(null)
                .childHandler(new ChildChannelHandler());
        try {
            //绑定了80端口,同步等待成功
            ChannelFuture future = server.bind(8080).sync();
            //关闭服务器应答
            future.channel().closeFuture().sync();
        }catch (Exception e){
            //优雅的关闭内容
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //客户端
            socketChannel.pipeline().addLast(new TimeServerChildHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeServer server = new TimeServer();
        server.start();
    }
}
