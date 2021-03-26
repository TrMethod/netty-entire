package com.cyzs.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author xh
 * @create 2019-12-11 12:00
 */
@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

        log.info(s);

        channelHandlerContext.channel().writeAndFlush("hello");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("出现错误，关闭连接" + cause.getMessage());
        //log.error(cause.getMessage(), cause);
        ctx.channel().close();
    }
}
