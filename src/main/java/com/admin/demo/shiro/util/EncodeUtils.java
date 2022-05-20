package com.admin.demo.shiro.util;

import jodd.util.Base64;
import org.apache.shiro.codec.Hex;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 编码工具类
 * 
 * @author Songwe
 * @date 2022/5/19 21:41
 */
public class EncodeUtils {

    /**
     * Hex 编码
     * @param bytes 输入数组
     * @return 编码字符串
     */
    public static String encodeHex(byte[] bytes) {
        return Hex.encodeToString(bytes);
    }

    /**
     * Hex 解码
     * @param code 解码字符串
     * @return 输出数组
     */
    public static byte[] decodeHex(String code) {
        return Hex.decode(code);
    }

    /**
     * Base64 编码
     * @param bytes 输入数组
     * @return 编码字符串
     */
    public static String encodeBase64(byte[] bytes) {
        return Base64.encodeToString(bytes);
    }

    /**
     * Base64 解码
     * @param code 编码字符串
     * @return 输出字符
     */
    public static byte[] decodeBase64(String code) {
        return Base64.decode(code);
    }
    
}
