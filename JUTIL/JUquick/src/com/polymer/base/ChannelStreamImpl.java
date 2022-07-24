package com.polymer.base;

import com.polymer.ibase.IConstant;
import com.polymer.ibase.IStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

/**
 * 使用NIO技术实现文件流的操作
 *      Channel(通道) ， Buffer(缓冲区) , Selector(选择器)
 * @see java.nio.channels.Channel
 * @see java.io.RandomAccessFile
 * @see java.nio.ByteBuffer
 * @author polymer
 * @since 2022年7月17日
 */
public class ChannelStreamImpl implements IStream {

    // 类公共的字符串缓存器
    private final StringBuilder builder=new StringBuilder();

    /**
     * 读取二进制文件流，通道技术
     *
     * @param sourceFilePath 文件完全路径
     * @return 缓存字符串
     * @throws Exception 文件路径或其它异常
     */
    @Override
    public StringBuilder nioChannelReader(String sourceFilePath) throws Exception {
        RandomAccessFile file = new RandomAccessFile(sourceFilePath, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        int count = fileChannel.read(buffer);
        while (count >0) {
            buffer.flip();
            CharBuffer charBuffer = CharBuffer.allocate((int)file.length());
            Charset charset = Charset.forName(IConstant.CHAR_SET);
            CharsetDecoder decoder = charset.newDecoder();
            decoder.decode(buffer, charBuffer, true);
            charBuffer.flip();
            while (charBuffer.hasRemaining()){
                builder.append(charBuffer.get());
            }
            buffer.clear();
            count = fileChannel.read(buffer);
        }
        try {
            fileChannel.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * 写出文件，NIO技术
     * 输出文件不存在，默认新建
     * 返回结果集数据格式：
     * {"result":"successful","exception":"","data":[object:{...}]}
     *
     * @param outFilePath 输出文件路径
     * @param builder 输出内容
     * @return 结果集合
     * @throws Exception 错误消息
     * @see
     * @since 2022年7月17日
     */
    @Override
    public List<Map<Object,Object>> nioChannelWriter(StringBuilder builder,String outFilePath) throws Exception {
        //内容非空校验
        if(builder==null||builder.length()<=0)return Result.successful();
        //-- 若文件不存在，则默认创建
        File f = new File(outFilePath);
        if(!f.canExecute())
            if(!(f.createNewFile()))return Result.successful();
        // 开始准备写出
        RandomAccessFile file = new RandomAccessFile(outFilePath, "rw");
        FileChannel fileChannel = file.getChannel();
        //使用直接分配内存方式
        int capacity=(int)(builder.capacity()*1.28);
        ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);
        buffer.put(builder.toString().getBytes(IConstant.CHAR_SET));
        buffer.flip();
        fileChannel.write(buffer);
        try {
            buffer.clear();
            fileChannel.close();
            file.close();
        } catch (IOException e) {e.printStackTrace();}
        return Result.successful();
    }

    /**
     * 文件的完整拷贝
     *
     * @param frompath 读取的文件路径
     * @param topath   输出的文件路径
     * @return true|false
     * @throws Exception 异常
     */
    public boolean transfer(String frompath, String topath) throws Exception {
        long result = 0;
        FileChannel inChannel = FileChannel.open(Paths.get(frompath), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(topath), StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // is opened state of channels
        if(inChannel.isOpen()&&outChannel.isOpen()){
            result = inChannel.transferTo(0, inChannel.size(), outChannel);
            // 或者可以使用下面这种方式
//            result=outChannel.transferFrom(inChannel, 0, inChannel.size());
            inChannel.close();
            outChannel.close();
        }
        return result > 0;
    }

}
