package com.cyzs.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * description:
 * author: xh
 * create: 2020-09-10 10:08
 */
public class ServerChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //参数1 读  参数2 写  参数三 读写   不关心就置为0
        pipeline.addLast(new IdleStateHandler(5, 5, 0, TimeUnit.SECONDS));

        //解码器
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        //解码之后处理
        pipeline.addLast("MyHandler", new MyHandler());
    }
}
