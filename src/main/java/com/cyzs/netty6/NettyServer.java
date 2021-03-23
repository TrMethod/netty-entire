package com.cyzs.netty6;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description: netty websocket and http
 * @author: xh
 * @create: 2020-04-28 8:51
 */
public class NettyServer {

    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup group1 = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //Handler  childHandler是交给group1处理
            serverBootstrap.group(group, group1).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ServerInitializer());
            //
            ChannelFuture channelFuture = serverBootstrap.bind(9900).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            group.shutdownGracefully();
            group1.shutdownGracefully();
        }

    }
}
