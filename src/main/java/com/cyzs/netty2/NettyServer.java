package com.cyzs.netty2;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**  处理tcp连接
 * @Author xiaoh
 * @create 2019-09-07 22:52
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup group1 = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //Handler  childHandler是交给group1处理
            serverBootstrap.group(group,group1).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            //
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            group.shutdownGracefully();
            group1.shutdownGracefully();
        }

    }
}
