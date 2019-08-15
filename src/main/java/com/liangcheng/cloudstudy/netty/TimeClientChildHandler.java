package com.liangcheng.cloudstudy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/9 15:19
 */
public class TimeClientChildHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf firstMessage;

    public TimeClientChildHandler(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            sb.append("hello lc ");
        }
        byte[] bytes = sb.toString().getBytes();
        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("hello lc ");

            byte[] bytes = sb.toString().getBytes();
            firstMessage = Unpooled.buffer(bytes.length);
            firstMessage.writeBytes(bytes);
            ctx.writeAndFlush(firstMessage);
        }
//        ctx.writeAndFlush(firstMessage);
//        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println("client receive the msg:" + new String(req,"UTF-8"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
