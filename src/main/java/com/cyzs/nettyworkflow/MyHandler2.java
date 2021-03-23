package com.cyzs.nettyworkflow;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: xh
 * @create: 2020-07-20 9:44
 */
public class MyHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] b = (byte[]) msg;

        System.out.println(b.length);
        System.out.println(new String(b, StandardCharsets.UTF_8));

        Channel channel = ctx.channel();
        channel.writeAndFlush("hello");

        //channel.closeFuture();
    }
}
