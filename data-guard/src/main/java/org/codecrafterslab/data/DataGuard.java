package org.codecrafterslab.data;

public interface DataGuard {

    /**
     * 加密数据
     *
     * @param plainText 明文字节码
     * @return 密文字节码
     */
    byte[] encrypt(byte[] plainText);

    /**
     * 解密数据
     *
     * @param cipherText 密文字节码
     * @return 明文字节码
     */
    byte[] decrypt(byte[] cipherText);

    /**
     * 是否为本地加密算法
     *
     * @return true:本地加密算法，false:非本地加密算法
     */
    default boolean isNative() {
        return false;
    }
}
