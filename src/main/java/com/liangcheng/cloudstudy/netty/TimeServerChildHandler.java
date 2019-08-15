package com.liangcheng.cloudstudy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author lc
 * @version 1.0
 * @date 2019/8/9 14:53
 */
public class TimeServerChildHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        count ++;
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println("server receive the msg:" + new String(req,"UTF-8") + new Date());
        ByteBuf resp = Unpooled.copiedBuffer((new Date().toString() + ":" + count).getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
