package com.cyzs.netty5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author xh
 * @create 2019-12-11 12:00
 */
public class MyServerHandler extends SimpleChannelInboundHandler<byte[]> {

    File file = new File("1.txt");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    FileWriter fileWriter = new FileWriter(file);

    public MyServerHandler() throws Exception {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] s) throws Exception {
        System.out.println(s.length);
        if (s.length <100){
            fileWriter.write(new String(s, StandardCharsets.UTF_8) + "\n");
            fileWriter.flush();
        }
        channelHandlerContext.writeAndFlush(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        SocketAddress socketAddress1 = ctx.channel().localAddress();

        System.out.println("通道活跃 " + socketAddress.toString() + " " + socketAddress1.toString());
        super.channelActive(ctx);
    }
}
