package com.liangcheng.cloudstudy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/9 15:12
 */
public class TimeClient {
    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        client.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel
                        socketChannel.pipeline().addLast(new TimeClientChildHandler());
                    }
                });
        try {
            ChannelFuture f = client.connect("localhost", 8080).sync();
            f.channel().closeFuture().sync();
        }catch (InterruptedException e){
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        TimeClient client = new TimeClient();
        client.connect();
    }
}
