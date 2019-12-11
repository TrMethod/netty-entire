package com.cyzs.javanio;



import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * @Description:
 * @Author xh
 * @create 2019-12-10 9:31
 */
public class JavaNioTest {

    @Test
    public void testA() throws Exception{

        FileInputStream inputStream = new FileInputStream("abc1.txt");
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        channel.read(byteBuffer);

        System.out.println(byteBuffer.toString());
    }

    public static void main(String[] args) throws Exception{
        File file = new File("C:\\DD\\IdeaProject\\2019\\netty-entire\\src\\main\\java\\com\\cyzs\\javanio\\abc.txt");
        //只有读权限
        FileInputStream inputStream = new FileInputStream(file);
        //只有写权限
        FileOutputStream outputStream = new FileOutputStream(file,true);
        FileChannel channel1 = outputStream.getChannel();
        //自己设置权限
        RandomAccessFile accessFile = new RandomAccessFile(file,"rws");
        FileChannel channel = accessFile.getChannel();
        //FileChannel channel = inputStream.getChannel();
        /**
         * buffer中的  position根据存放的数据变动 limit capacity
         * 如果数据很大，一次读取不完，怎么办，自己不做会流失数据
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        StringBuffer stringBuffer = new StringBuffer();
        //如果一次没有读完，就循环在次读，重新放到缓存中
        while (true){
            //如果有数据，返回数据的长度，如果返回 0 是数据放满了 ，如果返回-1 数据读完了
            int read = channel.read(byteBuffer);
            if (read > 0){
                byteBuffer.flip();

                // remaining()返回值是limit - position的值
                while (byteBuffer.remaining() > 0){
                    byte b = byteBuffer.get();
                    stringBuffer.append((char) b);
                }
            }else {
                break;
            }
            //是position为 0，limit = capacity，可以重新向里面放数据了
            byteBuffer.clear();
            System.out.println("1111");
        }
        String s = stringBuffer.toString();
        System.out.println(s);

        byteBuffer.put("fffff".getBytes());
        byteBuffer.flip();
        // 读数据和写数据还是要建立两个channel
        //RandomAccessFile建立的channel读写都可以
        channel.write(byteBuffer);

        /**
         *关闭资源
         */
        System.out.println("结束");
        channel.close();
        channel1.close();
    }

    /**
     * buffer有没有初始值   初始值为 0
     */
    @Test
    public void testB(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte b1 = 47;
        buffer.put(b1);
        buffer.flip();
        int i = 0;
        while (buffer.hasRemaining()){
            byte b = buffer.get();
            System.out.println((char)b);

        }
    }
}
