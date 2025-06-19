package org.codecrafterslab.data.impl;

import org.codecrafterslab.data.DataGuard;

public class NativeDataGuard implements DataGuard {

    public native byte[] encrypt(byte[] plainText);

    public native byte[] decrypt(byte[] cipherText);
}
