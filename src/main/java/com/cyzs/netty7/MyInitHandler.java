package com.cyzs.netty7;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * description:
 * author: xh
 * create: 2020-08-20 16:43
 */
public class MyInitHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec http请求解码和响应编码器 相当于HttpRequestDecoder 和HttpResponseEncoder
        //pipeline.addLast(new HttpServerCodec());
        //可以大文件传输
        /*pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));
        pipeline.addLast(new StringDecoder(StandardCharsets.UTF_8));*/

        pipeline.addLast(new LineBasedFrameDecoder(3072));
        pipeline.addLast(new ByteArrayEncoder());
        pipeline.addLast(new ByteArrayDecoder());

        pipeline.addLast("MyHandler", new MyHandler());


    }
}
