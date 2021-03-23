package com.cyzs.serialize;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * create: xh  2020-12-28 10:00
 */
public class Server {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup group1 = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group,group1).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInit());

            ChannelFuture channelFuture = serverBootstrap.bind(9900).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
            group1.shutdownGracefully();
        }

    }
}
