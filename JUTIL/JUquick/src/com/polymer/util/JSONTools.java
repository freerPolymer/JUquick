package com.polymer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polymer.base.Type;
import com.polymer.ibase.IConstant;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*
 <b>选择 Jackson作为开发的缘由</b>
 <p>
        1. jackson文档笔记全面，开发人数相对多，版本迭代相对活跃
        2.jackson性能和稳定性是综合最好的，GSON全面，但庞大，功能多余，fastjson性能卓越，但频繁爆出漏洞
        3.  jackson作为springboot框架的一部分，其可用性和认可度是很高的
 </p>
 */

/**
 * JSON数据序列化与反序列化
 * 通过 获得一个JSON格式字符串
 * 通过  获得一个JAVA对象
 */
public class JSONTools {

    //声明
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Map<String, Object> mp = new HashMap<>();

    static {
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        // 如果存在未知属性，则忽略不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许key没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许key有单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //读取时间戳到纳秒
        mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        // 使用数组
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    }

    /**
     * map对象序列化
     *
     * @param source 对象
     * @param format  格式化
     * @return 字符串
     */
    public static String parseJSONString(Map<Object, Object> source,boolean format) {
        try {
            if (source != null && source.size() > 0)
                return format?mapper.writerWithDefaultPrettyPrinter().writeValueAsString(source):mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
        }
        return IConstant.JSON_OBJECT_EMPTY;
    }

    /**
     * List对象数组序列化
     * resultFile  为空不写入本地文件
     * 文件类型必须是 [*.json]
     *
     * @param source     集合
     * @param format  格式化
     * @param resultFile 文件
     * @return 字符串
     */
    public static String parseJSONString( Map<String,Object> source, File resultFile,boolean format) {
        try {
            if (source != null && source.size() > 0) {
                //写出到文件
                if (resultFile != null && resultFile.isFile() && resultFile.getName().toLowerCase().lastIndexOf(".json") > 0) {
                    //  create a new file of default
                    if (!resultFile.exists())
                        resultFile.createNewFile();
                    mapper.writeValue(resultFile, source);
                }
                return format?mapper.writerWithDefaultPrettyPrinter().writeValueAsString(source):mapper.writeValueAsString(source);
            }
        } catch (Exception e) {
        }
        return IConstant.JSON_OBJECT_EMPTY;
    }

    /**
     * 反序列化
     *
     * @param jsonSRC 合格的JSON文件
     * @return MAP对象
     */
    public static Map<String, Object> parseJAVAMap(File jsonSRC) {
        try {
            if (jsonSRC != null) {
                mp = mapper.readValue(jsonSRC, Map.class);
            }
        } catch (IOException ignored) {
        }
        return mp;
    }

    /**
     * 反序列化
     *
     * @param json 符合规则的JSON字符串
     * @return MAP对象
     */
    public static Map<String, Object> parseJAVAMap(String json) {
        try {
            if (json != null && json.length() > 0) {
                mp = mapper.readValue(json, Map.class);
            }
        } catch (Exception ignored) {
        }
        return mp;
    }

    /**
     * 反序列化
     * 返回一个完整的JAVA任意类型对象
     * json 格式必须合法
     * 或
     * jsonSRC是有效的JSON文件
     *
     * @param json    字符串
     * @param jsonSRC 文件路径
     * @return Java对象
     */
    public static Object parseJAVAObject(String json, File jsonSRC) {
        Object object = null;
        try {
            if (jsonSRC != null && jsonSRC.getName().lastIndexOf(".json") > 0) {
                object = mapper.readValue(jsonSRC, Object.class);
            }
            if (json != null && json.length() > 0)
                object = mapper.readValue(json, Object.class);
        } catch (Exception ignored) {
        }
        return object;
    }

    /**
     * json字符串转换为JAVA对象
     *
     * @param json  待转换JSON
     * @param clazz 转换为对象类型
     * @param <T>   换对象类型
     * @return 对象
     */
    public static <T> Type<T> parseJAVA(String json, Class<T> clazz) {
        if ((json == null || json.length() < 1) || clazz == null) {
            return Type.empty();
        }
        if (clazz.equals(String.class)) {
            return Type.init((T) json);
        }
        try {
            T obj = mapper.readValue(json, clazz);
            return Type.init(obj);
        } catch (Exception e) {}
        return Type.empty();
    }
}
