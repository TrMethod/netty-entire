package com.cyzs.netty6;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * @description:
 * @author: xh
 * @create: 2020-04-28 13:18
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpServerCodec http请求解码和响应编码器 相当于HttpRequestDecoder 和HttpResponseEncoder
        pipeline.addLast(new HttpServerCodec());
        //可以大文件传输
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自己的处理器
        //pipeline.addLast(new MyTextWebSocketHandler());
        pipeline.addLast(new HttpHandler());

    }
}
