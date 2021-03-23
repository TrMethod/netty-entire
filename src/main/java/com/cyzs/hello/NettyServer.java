package com.cyzs.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * description:
 * author: xh
 * create: 2020-09-10 10:01
 */
public class NettyServer {

    public static void main(String[] args) {

        //一个负责接请求一个负责处理
        NioEventLoopGroup reception = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.handler(new LoggingHandler(LogLevel.TRACE));

            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(NioChannelOption.SO_KEEPALIVE,true);

            serverBootstrap.group(reception, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInit());

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){

            e.printStackTrace();
            reception.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
