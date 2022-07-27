package com.polymer.util;

import com.polymer.base.CharProducer;
import com.polymer.ibase.IConstant;

import java.util.Random;

/**
 字符随机生产者，char random producer
    static 是静态的属性，不会被GC，所以在使用之前灵活选择。
 */

public class CRPUtils {
    /*
    获取随机字符
    @return char,每次返回一个字符
     */
    public  static  char getRandomChar(){
        return CharProducer.randomChar();
    }

    /**
    获取随机字符，长度自定义
    @param length 最小为1
    @return String 字符串，至少返回一个
     */
    public  static  String getRandomChar(int length){
        //做校验防止内存溢出
        if(length<IConstant.MIN_OPERAND||length>IConstant.MAX_OPERAND)return IConstant.NO_VALUE;
        return CharProducer.getRandomChar(length);
    }

    /**
   获取随机字符，长度自定义
     @param sourceStr 自定义字符串
   @param length 最小为1
   @return String 字符串，至少返回一个
    */
    public  static  String getRandomChar(String sourceStr,int length){
        if(length<IConstant.MIN_OPERAND||length>IConstant.MAX_OPERAND)return IConstant.NO_VALUE;
        // 如果输入字符串为空，默认生产数字+字母的随机字符
        if(sourceStr==null||sourceStr.length()==0)sourceStr=IConstant.INTEGER_ARRAY.toString();
        return CharProducer.getRandomChar(sourceStr,length);
    }

}
