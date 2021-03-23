package com.cyzs.netty7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * description: 用来解析http
 * author: xh
 * create: 2020-08-20 16:37
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup parent = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(parent, group).channel(NioServerSocketChannel.class)
                    .childHandler(new MyInitHandler());

            ChannelFuture sync = serverBootstrap.bind(9900).sync();
            sync.channel().closeFuture().sync();


        }catch (Exception e){
            group.shutdownGracefully();
            parent.shutdownGracefully();
        }
    }
}
