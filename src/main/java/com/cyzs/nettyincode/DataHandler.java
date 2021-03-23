package com.cyzs.nettyincode;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.FileOutputStream;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author: xh  2020-11-12 13:06
 */
public class DataHandler extends SimpleChannelInboundHandler<String>{

    public ArrayList<Channel> channelList = new ArrayList<>(16);

    public static FileOutputStream outputStream = null;
    static {
        try {
            outputStream = new FileOutputStream("data_rev_file");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String s = msg + "end";
        System.out.println(s);
        String s1 = s + "\n";
        outputStream.write(s1.getBytes(StandardCharsets.UTF_8));
        Channel channel = ctx.channel();
        // 获取远程地址
        SocketAddress socketAddress = channel.remoteAddress();
        System.out.println(socketAddress.toString());
    }

    /**
     * 在channelRead0之前
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        channelList.add(ctx.channel());
        System.out.println(channelList.size());
    }

    /**
     * 在异常之后回调
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        System.out.println(channelList.size());
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println(channelList.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常---");
        System.out.println(cause.getMessage());
        cause.printStackTrace();
    }
}
