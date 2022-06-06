package com.admin.common.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成摘要
 * @author Songwe
 * @date 2022/5/19 21:58
 */
public class DigestUtils {
    private static final String SHA1 = "SHA-1";
    
    private static final int HASH_ITERATIONS = 512;

    /**
     * SHA1 摘要算法
     * @param password 源明文串
     * @param salt 干扰元素
     * @return
     */
    public static String sha1(String password, String salt) {
        return new SimpleHash(SHA1, password, salt, HASH_ITERATIONS).toString();
    }

    /**
     * 生成随机 salt
     * @return HEX 编码 salt
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        return secureRandomNumberGenerator.nextBytes().toHex();
    }

    /**
     * 生成密码和 salt
     * @param password 明文密码
     * @return map -> salt 和密文密码
     */
    public static Map<String, String> encryptPassword(String password) {
        String salt = generateSalt();
        String encryptedPassword = sha1(password, salt);
        HashMap<String, String> map = new HashMap<>();
        map.put("salt", salt);
        map.put("password", encryptedPassword);
        return map;
    }
}
