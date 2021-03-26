package com.cyzs.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * @author: xh  2021-03-25 10:56
 */
public class NettyClient {

    public static void main(String[] args) throws Exception{
        // java 原声版
        /* SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(9000));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    try {
                        socketChannel.write(StandardCharsets.UTF_8.encode(s + "\n"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("send\n");
                }
            }
        }).start();


        Thread.currentThread().join();*/


        // netty 版
        ChannelFuture channelFuture = new Bootstrap().group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress(9000));

        channelFuture.sync();

        Channel channel = channelFuture.channel();
        //channel.writeAndFlush("hello");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    channel.writeAndFlush(s);
                    System.out.println("send\n");
                }
            }
        }).start();
    }
}
