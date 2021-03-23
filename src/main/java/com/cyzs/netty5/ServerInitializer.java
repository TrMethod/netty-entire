package com.cyzs.netty5;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;


/**
 * @Author xiaoh
 * @create 2019-09-07 23:01
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /**
         *  ChannelPipeline加工channel获取的数据
         */
        ChannelPipeline pipeline = socketChannel.pipeline();
        SocketChannelConfig config = socketChannel.config();
        config.setRecvByteBufAllocator(new FixedRecvByteBufAllocator(32768));
        pipeline.addLast(new ByteArrayEncoder());
        pipeline.addLast(new ByteArrayDecoder());
        //自己实现逻辑处理
        pipeline.addLast("myServerHander",new MyServerHandler());
    }
}
