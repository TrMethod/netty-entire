package com.cyzs.test;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**  处理tcp连接
 * @Author xiaoh
 * @create 2019-09-07 22:52
 */

@Slf4j
public class NettyServer {
    public static void main(String[] args) throws Exception {

        log.info("http://localhost:9000/");

        NioEventLoopGroup group = new NioEventLoopGroup(2);
        NioEventLoopGroup group1 = new NioEventLoopGroup(2);
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(group, group1).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            //
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            group.shutdownGracefully();
            group1.shutdownGracefully();
        }

    }
}
