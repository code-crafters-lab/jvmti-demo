package org.codecrafterslab.data.impl;

import org.codecrafterslab.DataGuardContext;
import org.codecrafterslab.data.DataGuard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDataGuardTest {
    DataGuard dataGuard;
    String plain = "Hello World! 007，欢迎来到编程世界。";
    String cipher = "hELLO wORLD! 118，欢迎来到编程世界。";

    @BeforeEach
    void setUp() {
        dataGuard = DataGuardContext.currentDataGuard();
    }

    @AfterEach
    void tearDown() {
        dataGuard = null;
    }

    @Test
    void encrypt() {
        byte[] encrypted = dataGuard.encrypt(plain.getBytes(StandardCharsets.UTF_8));
        assertEquals(new String(encrypted, StandardCharsets.UTF_8), cipher);
    }

    @Test
    void decrypt() {
        byte[] decrypted = dataGuard.decrypt(cipher.getBytes(StandardCharsets.UTF_8));
        assertEquals(new String(decrypted,StandardCharsets.UTF_8), plain);
    }
}
