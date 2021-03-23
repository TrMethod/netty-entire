package com.cyzs.netty7;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

/**
 * description:
 * author: xh
 * create: 2020-08-20 16:48
 */
public class MyHandler extends SimpleChannelInboundHandler<byte[]> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {


        System.out.println(msg.length);
        System.out.println(new String(msg, StandardCharsets.UTF_8));

       /* String s = "HTTP/1.1 200 OKDate: Tue, 12 Jul 2016 21:36:12 GMT\n Content-Length: 563Content-Type: text/html\n<html> \n<body>Hello http! </body> </html>";
        ctx.channel().write(s);
        ctx.flush();
        ctx.channel().closeFuture();*/

    }


}
