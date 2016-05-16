package com.xtuapp.zsxd.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class PrefUtils {

    public static final String XTU_SP = "xtu_sp";

    public static String REGEX_EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    public static String REGEX_PASSWORD = "^[a-z0-9_-]{6,18}$";
    public static String REGEX_STUDENT_ID = "^\\d{10,12}$";
    /**
     * 保存数据到SP
     *
     * @param context
     *            上下文
     * @param key
     *            以该key保存数据
     * @param value
     *            数据的value
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(XTU_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (editor != null) {
            if (value != null) {
                if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                }
                if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                }

                if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                }

                if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                }

                if (value instanceof String) {
                    editor.putString(key, (String) value);
                }
            }
            editor.commit();
        }
    }

    /**
     * 从SP中获取数据
     *
     * @param <T>
     * @param context
     *            上下文
     * @param clazz
     *            获取数据的类型对应的Class
     * @param key
     *            保存数据的key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Context context, Class<T> clazz, String key) {
        Object object = null;
        if (context != null && clazz != null) {
            SharedPreferences sp = context.getSharedPreferences(XTU_SP, Context.MODE_PRIVATE);
            String name = clazz.getName().substring(10);
            if (name.equals("Boolean")) {
                object = sp.getBoolean(key, false);
            } else if (name.equals("Float")) {
                object = sp.getFloat(key, -1);
            } else if (name.equals("Integer")) {
                object = sp.getInt(key, -1);
            } else if (name.equals("Long")) {
                object = sp.getLong(key, -1);
            } else if (name.equals("String")) {
                object = sp.getString(key, null);
            }
        }
        return (T) object;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static boolean validate(String text, String regex) {
        if (text == null) {
            return false;
        }
        return text.matches(regex);
    }

    // 16位uuid
    public static String uuid() {
        return UUID.randomUUID().toString().substring(9, 28).replaceAll("\\-", "");
    }

    public static String getGMT() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss 'GMT 0800 (中国标准时间)'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        return sdf.format(cd.getTime());
    }

    /**
     * MD5加密(16位)
     *
     * @param input
     * @return
     */
    public static String md5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().substring(8, 24);

    }

//    /**
//     * AES 加密
//     *
//     * @param content
//     *            待加密的字符串
//     * @param encryptKey
//     *            密钥
//     * @return
//     * @throws Exception
//     *             密文
//     */
//    public static String aesEncrypt(String content, String encryptKey) throws Exception {
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        kgen.init(128, new SecureRandom(encryptKey.getBytes()));
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
////        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes("utf-8")));
//        return Base64.encodeToString(cipher.doFinal(content.getBytes("utf-8")),Base64.DEFAULT);
//    }

    /**
     * 加密
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public static String encrypt(String text, String key) {
        if (text == null || text.isEmpty() || key == null || key.isEmpty()) {
            return null;
        }
        final String table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*-";
        byte[] textBytes = text.getBytes();
        int remain = 0, remainBitCount = 0;
        StringBuilder builder = new StringBuilder();
        byte[] keyByte = new byte[textBytes.length + 2];
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        for (int i = 0; i < keyByte.length; i++) {
            int index = i, loopHash = 0;
            if (index >= key.length()) {
                index %= key.length();
            }
            while (index < key.length()) {
                loopHash = (loopHash << 4) ^ (loopHash >> 28) ^ key.charAt(index) * key.length();
                index += keyByte.length;
            }
            keyByte[i] = (byte) (loopHash ^ hash);
        }
        for (int i = 0; i < textBytes.length + 2; i++) {
            int textByte;
            if (i == 0) {
                // 加上正文开始标记
                textByte = (0x02 ^ keyByte[i]) & 0xFF;
            } else if (i == textBytes.length + 1) {
                // 加上正文结束标记
                textByte = (0x03 ^ keyByte[i]) & 0xFF;
            } else {
                textByte = (textBytes[i - 1] ^ keyByte[i]) & 0xFF;
            }
            // 获取b的高(6 - remainBitCount)位
            int hight = textByte >>> (2 + remainBitCount) & 0xFF;
            // 与上一轮的余留组合成一个字节
            int curr = remain << (6 - remainBitCount) | hight;
            // builder.append(table.charAt(curr));
            builder.append(table.charAt(curr));
            // 剩余b的低(2 + remainBitCount)位
            remain = hight << (2 + remainBitCount) ^ textByte;
            remainBitCount += 2;
            // 若剩余的位只好为6位，则进行编码
            if (remainBitCount == 6) {
                builder.append(table.charAt(remain));
                remainBitCount = 0;
                remain = 0;
            }
        }
        if (remainBitCount != 0) {
            // 多出的位到末尾补0凑够6位
            builder.append(table.charAt(remain << (6 - remainBitCount)));
        }
        return builder.toString();
    }

    /**
     * 解密
     *
     * @param text
     * @param key
     * @return
     */
    public static String decrypt(String text, String key) {
        if (text == null || text.isEmpty() || key == null || key.isEmpty()) {
            return null;
        }
        byte[] resultByte = new byte[text.length() * 6 / 8];
        byte[] keyByte = generateByteArray(key, resultByte.length);
        int pos = 0, remain = 0, remainBitCount = 0;
        final String table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*-";
        for (int i = 0; i < text.length(); i++) {
            int code = table.indexOf(text.charAt(i));
            if (remainBitCount + 6 < 8) {
                // 若不足一个字节
                remain = remain << 6 | code;
                remainBitCount += 6;
            } else {
                // 取code的高(8 - remainBitCount)位
                int high = code >>> (remainBitCount - 2) & 0xFF;
                resultByte[pos] = (byte) (remain << (8 - remainBitCount) | high);
                resultByte[pos] ^= keyByte[pos];
                pos++;
                remainBitCount -= 2;
                remain = high << remainBitCount ^ code;
            }
        }
        if (resultByte[0] == 2 && resultByte[resultByte.length - 1] == 3) {
            return new String(resultByte, 1, resultByte.length - 2);
        }
        return null;
    }
    private static byte[] generateByteArray(String key, int len) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        byte[] bs = new byte[len];
        for (int i = 0; i < len; i++) {
            int index = i, loopHash = 0;
            if (index >= key.length()) {
                index %= key.length();
            }
            while (index < key.length()) {
                loopHash = (loopHash << 4) ^ (loopHash >> 28) ^ key.charAt(index) * key.length();
                index += len;
            }
            bs[i] = (byte) (loopHash ^ hash);
        }
        return bs;
    }
    //dp-----xp
    public static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().densityDpi;
        return (int)(dipValue*(scale/160));
    }
    //xp-----dp
    public static int px2dp(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int)((pxValue*160)/scale);
    }
}
