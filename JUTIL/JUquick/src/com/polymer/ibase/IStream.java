package com.polymer.ibase;

import java.util.List;
import java.util.Map;

/**
 流操作基本定义接口
    该接口依照Java API接口规范定义。
    提供流操作的顶级接口定义。
 @since 2022年7月17日
 @author polymer
 */

public interface IStream {

    /**
     * 读取二进制文件流，通道技术
     * @param sourceFilePath 文件完全路径
     * @return  缓存字符串
     * @throws Exception 文件路径或其它异常
     */
    public StringBuilder nioChannelReader(String sourceFilePath)throws Exception;

    /**
     * 写出文件，NIO技术
     *      输出文件不存在，默认新建
     *      返回结果集数据格式：
     *      {"result":"successful","exception":"","data":[object:{...}]}
     * @param outFilePath 输出文件路径
     * @param builder 输出内容
     * @return 结果集合
     * @throws Exception 错误消息
     * @since 2022年7月17日
     */
    public  List<Map<Object,Object>> nioChannelWriter(StringBuilder builder,String outFilePath)throws Exception;

    /**
     * 文件的完整拷贝
     * @param frompath 读取的文件路径
     * @param topath 输出的文件路径
     * @return true|false
     * @throws Exception 异常
     */
    public  boolean transfer(String frompath,String topath) throws Exception;
}
