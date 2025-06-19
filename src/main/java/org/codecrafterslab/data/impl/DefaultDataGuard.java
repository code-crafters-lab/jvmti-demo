package org.codecrafterslab.data.impl;

import org.codecrafterslab.data.DataGuard;

public class DefaultDataGuard implements DataGuard {
    @Override
    public byte[] encrypt(byte[] plainText) {
        // TODO: 2025/06/19 16:20 实现加密算法
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        // TODO: 2025/06/19 16:20 实现解密算法
        return new byte[0];
    }

}
