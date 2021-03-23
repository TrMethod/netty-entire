package com.cyzs.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * description:
 * author: xh
 * create: 2020-09-10 13:20
 */
public class IdleHandler extends SimpleChannelInboundHandler<IdleStateEvent> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IdleStateEvent msg) throws Exception {
        System.out.println("IdleHandler.....");
        IdleState state = msg.state();
        if (state == IdleState.READER_IDLE){
            System.out.println("检测到20秒未读");
            ctx.channel().close();
        }

    }
}
