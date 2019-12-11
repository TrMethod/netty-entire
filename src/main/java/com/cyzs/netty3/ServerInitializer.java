package com.cyzs.netty3;



import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @Author xiaoh
 * @create 2019-09-07 23:01
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //
        pipeline.addLast(new StringEncoder(Charset.forName("UTF-8")));
        pipeline.addLast(new StringDecoder(Charset.forName("UTF-8")));
        //pipeline.addLast(new ByteArrayEncoder());
        //自己实现逻辑处理
        pipeline.addLast("myServerHander",new MyServerHandler());
    }
}
