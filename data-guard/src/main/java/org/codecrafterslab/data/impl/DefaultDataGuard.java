package org.codecrafterslab.data.impl;

import org.codecrafterslab.data.DataGuard;

public class DefaultDataGuard implements DataGuard {
    /* 偏移步长 */
    private static final int SHIFT_STEP = 1;

    @Override
    public byte[] encrypt(byte[] plainText) {
        if (plainText == null) return null;
        byte[] result = new byte[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            char c = (char) plainText[i];
            result[i] = (byte) characterConversion(c, SHIFT_STEP);
        }
        return result;
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        if (cipherText == null) return null;
        byte[] result = new byte[cipherText.length];
        for (int i = 0; i < cipherText.length; i++) {
            char c = (char) cipherText[i];
            result[i] = (byte) characterConversion(c, -SHIFT_STEP);
        }
        return result;
    }

    /**
     * <p>字符转换</p>
     * <p>1. 交换字母大小写（大写→小写，小写→大写）</p>
     * <p>2. 对数字进行循环移位（如 '5'+1 → '6'，'9'+1 → '0'）</p>
     * <p>3. 其他字符保持不变</p>
     *
     * @param c      字符
     * @param offset 数字偏移量
     * @return chart
     */
    private char characterConversion(char c, int offset) {
        offset %= 10;
        if (Character.isUpperCase(c)) {
            return Character.toLowerCase(c);
        } else if (Character.isLowerCase(c)) {
            return Character.toUpperCase(c);
        } else if (Character.isDigit(c)) {
            int digit = c - '0';
            digit = (digit + offset + 10) % 10;
            return (char) ('0' + digit);
        }
        return c;
    }

}
