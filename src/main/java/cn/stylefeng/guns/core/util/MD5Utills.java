package cn.stylefeng.guns.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.security.MessageDigest;

/**
 * Description: xbcard-xbcard
 * Created by : qunge
 * Date : 2019/12/10 17:59
 */
public class MD5Utills {
    /**
     * MD5加密之方法一
     *
     * @param str 待加密字符串
     * @return 16进制加密字符串
     * @explain 借助apache工具类DigestUtils实现
     */
    public static String encryptToMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static void main(String[] args) {
        System.out.println(encryptToMD5("dffkjjkzzzzzzzzzzzzeezzzzzzzzzzzzzzzzzzzzzzeewkwekwlekwlkelwkewlkelweklwkelweklwkelwkelwke"));
    }


    /**
     * MD5加密之方法二
     * @explain java实现
     * @param str
     *            待加密字符串
     * @return 16进制加密字符串
     */
    public static String encrypt2ToMD5(String str) {
        // 加密后的16进制字符串
        String hexStr = "";
        try {
            // 此 MessageDigest 类为应用程序提供信息摘要算法的功能
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 转换为MD5码
            byte[] digest = md5.digest(str.getBytes("utf-8"));
            hexStr = ByteUtils.toHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexStr;

    }

}
