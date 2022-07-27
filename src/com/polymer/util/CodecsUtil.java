package com.polymer.util;

import com.polymer.base.Codecs;

import javax.validation.constraints.*;

/**
 * 编码|解码工具类
 *      提供字符串编译成密文
 *      可使用解码方法进行反向解码，还原字符串
 * @author polymer
 * @since 20220727
 *
 */
public class CodecsUtil {

    private static final Codecs codecs = new Codecs();

    /**
     * MD5加密 - 不可逆加密
     * @param res   需要加密的字符串
     * @param key   密钥
     * @return
     */
    public static String MD5(@NotNull String res, String key){
        return (key!=null&&key.length()>0)?codecs.MD5(res,key): codecs.MD5(res);
    }

    /**
     * SHA1加密 - 不可逆
     * @param res 加密字符串
     * @param key 密钥
     * @return
     */
    public static String SHA1(@NotNull String res,String key){
        return (key!=null&&key.length()>0)?codecs.SHA1(res,key):codecs.SHA1(res);
    }

    /**
     * DES 可逆
     *  加密  |   解密
     * @param res
     * @param key
     * @param isDecode false加密  |   true解密
     * @return
     */
    public static String DES(@NotNull String res, @NotNull String key,boolean isDecode){
        return isDecode?codecs.decodeDES(res,key):codecs.encodeDES(res,key);
    }

    /**
     * AES 可逆
     *  加密  |   解密
     * @param res
     * @param key
     * @param isDecode false加密  |   true解密
     * @return
     */
    public static String AES(@NotNull String res, @NotNull String key,boolean isDecode){
        return isDecode?codecs.decodeAES(res,key):codecs.encodeAES(res,key);
    }

    /**
     * XOR 可逆
     *  加密  |   解密
     * @param res
     * @param key
     * @param isDecode false加密  |   true解密
     * @return
     */
    public static String XOR(@NotNull String res, @NotNull String key,boolean isDecode){
        return isDecode?codecs.decodeXOR(res,key):codecs.encodeXOR(res,key);
    }

    /**
     * 获取异或值
     * @param res 密文
     * @param key 秘钥
     * @return
     */
    public static int XOR(@Min(value = 1) int res, @NotNull String key){
        return codecs.XOR(res,key);
    }

    /**
     * Base64 可逆
     *  加密  |   解密
     * @param res
     * @param isDecode false加密  |   true解密
     * @return
     */
    public static String Base64(@NotNull String res,boolean isDecode){
        return isDecode?codecs.decodeBase64(res):codecs.encodeBase64(res);
    }
}
