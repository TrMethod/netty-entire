package com.cyzs.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


/**
 * @author: xh  2021-03-25 15:11
 */
public class ByteBufTest {


    @Test
    public void test1(){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.heapBuffer(32);
        byteBuf.writeInt(15);
        byteBuf.writeInt(20);
        // 切出来的和byteBuf共用内存，不会开辟新的空间，零拷贝
        ByteBuf slice = byteBuf.slice(0, 8);
        slice.setInt(0, 1000);
        // 改变slice也就是改变byteBuf
        System.out.println(byteBuf.getInt(0) + "==" + slice.getInt(0));

        byteBuf.clear();// 写模式
        byteBuf.release(); // 引用器减一
    }



    @Test
    public void test2(){
        int a = 12;

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        ByteBuffer byteBuffer1 = byteBuffer.putInt(15);
        byteBuffer1.flip();
        System.out.println(byteBuffer1.getInt());
    }


}
