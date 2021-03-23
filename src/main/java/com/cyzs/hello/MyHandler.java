package com.cyzs.hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * description:
 * author: xh
 * create: 2020-09-10 10:11
 */
public class MyHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.decoderResult().toString());

        if (msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest) msg;
            HttpHeaders headers = httpRequest.headers();
            Iterator<Map.Entry<String, String>> entryIterator = headers.iteratorAsString();
            while (entryIterator.hasNext()){
                Map.Entry<String, String> next = entryIterator.next();
                System.out.println(next.getKey() + next.getValue());
            }

            ByteBuf content = Unpooled.copiedBuffer("Hello World", StandardCharsets.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            //关闭channel ，也可以不关，一直连接
            ctx.channel().close();

        }
    }
}
