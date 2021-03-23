package com.cyzs.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author xiaoh
 * @create 2019-09-07 23:01
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpServerCodec处理http请求
        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        //自己实现逻辑处理
        pipeline.addLast("myHttpServerHandler", new MyHttpServerHandler());
    }
}
