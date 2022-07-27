package com.polymer.util;

import com.polymer.base.ChannelStreamImpl;
import com.polymer.ibase.IConstant;
import com.polymer.ibase.IStream;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 该类是文件数据操作类。
    依赖java.io包下部分资源
    包含基本的字节、字符流输入操作
 *
 * <p>
 *     通过 allocate() 来获取缓冲区(buffer)
 *      put():存入数据到缓冲区中()
 *      get():获取缓冲区中的数据
 *      capacity: 容量，表示缓冲区中最大存储数据的容量。一旦声明不能更改。
 *      limit: 界限，表示缓冲区中可以操作数据的大小。（limit 后的数据不能进行读写）
 *      position: 位置，表示缓冲区中正在操作数据的位置。
 *      mark: 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置。
 *      0 <= mark <= position <= limit <= capacity
 *
 * </p>
 */
public class NIOStreamUtils {

    //面向接口编程
    private static final IStream stream = new ChannelStreamImpl();
    /**
     * 读取二进制文件流，通道技术
     *
     * @param sourceFilePath 文件完全路径
     * @return 缓存字符串
     * @throws Exception 文件路径或其它异常
     */
    public static StringBuilder nioChannelReader(String sourceFilePath) throws Exception{
        return stream.nioChannelReader(sourceFilePath);
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
     * @since 2022年7月17日
     */
    public static List<Map<Object,Object>> nioChannelWriter(StringBuilder builder, String outFilePath) throws Exception{
        return stream.nioChannelWriter(builder,outFilePath);
    }

    /**
     *  文件的拷贝
     *          frompath必须是有效的文件(可读)
     * @param frompath 需要拷贝文件
     * @param topath 目标文件
     * @return true|false
     * @throws Exception 异常
     */
    public static boolean transferTo(String frompath,String topath)throws Exception{
        if(frompath==null||topath==null)return false;
        if(frompath.length()<2||topath.length()<2)return false;
        if(!(checkFiletype(frompath)&&checkFiletype(topath)))return false;
        File file = new File(frompath);
        if(!(file.exists()&&file.canRead()))return false;
        return stream.transfer(frompath,topath);
    }

    /**
     * 检查文件类型(不区分大小写)
     * @param filepath 文件类型，如 DOC或doc
     * @return true|false
     */
    private static boolean checkFiletype(String filepath){
        if(filepath==null||filepath.length()<1)return false;
        String type = filepath.substring(filepath.lastIndexOf(".")+1).toUpperCase();
        if(type.length()<1)return false;
        if (Arrays.asList(IConstant.FILE_TYPE_TEXT).contains(type))return true;
        if (Arrays.asList(IConstant.FILE_TYPE_IMG).contains(type))return true;
        if (Arrays.asList(IConstant.FILE_TYPE_VIDEO).contains(type)) return true;
        return false;
    }
}
