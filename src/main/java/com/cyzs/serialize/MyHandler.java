package com.cyzs.serialize;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * create: xh  2020-12-28 10:03
 */
public class MyHandler extends SimpleChannelInboundHandler<byte[]> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {

        String s = new String(msg,StandardCharsets.UTF_8);
        System.out.println("--------------------");
        System.out.println(s);
        ctx.channel().closeFuture();
        ctx.channel().close();
    }
}
