package com.cyzs.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * 继承 SimpleChannelInboundHandler有一个泛型，这种只能接受泛型的类型
 * @Author xiaoh
 * @create 2019-09-07 23:05
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    /**
     * 方法调用时机
     * @param channelHandlerContext channelHandlerContext
     * @param httpObject httpObject
     * @throws Exception Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        try{
            if (httpObject instanceof HttpRequest){
                HttpRequest request = (HttpRequest) httpObject;
                System.out.println(request.uri());
                System.out.println(request.method().name());
                if ("/favicon.ico".equals(request.uri())){
                    return;
                }
                System.out.println("myhandler==");
                ByteBuf content = Unpooled.copiedBuffer("Hello World", StandardCharsets.UTF_8);
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
                channelHandlerContext.writeAndFlush(response);
                //关闭channel ，也可以不关，一直连接
                channelHandlerContext.channel().close();
            }else {
                //非http请求
                DecoderResult decoderResult = httpObject.decoderResult();
                System.out.println(decoderResult.toString());
                channelHandlerContext.writeAndFlush("hello");

            }
        }catch (Exception e){
             e.printStackTrace();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal注册！");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal 取消注册！");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal活跃！");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal不活跃");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal 读完成");

        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channal 可写");
        super.channelWritabilityChanged(ctx);
    }

    /**
     *  出现异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //把通道关闭
        ctx.channel().close();
        //cause.printStackTrace();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler 添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler 移除");
        super.handlerRemoved(ctx);
    }

}
