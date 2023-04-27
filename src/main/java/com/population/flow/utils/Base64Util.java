package com.population.flow.utils;

import java.util.Base64;

public class Base64Util {
    /***
     * BASE64解密
     * @param key
     * @return
     */
    public static byte[] decryBASE64(String key) {
        return Base64.getDecoder().decode(key);
    }

    /***
     * BASE64加密
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key) {
        return Base64.getEncoder().encodeToString(key);

    }
}
