package org.codecrafterslab.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

@Slf4j
public class DataGuardClassFileTransformer implements ClassFileTransformer {

    public static String DEFAULT_DATA_GUARD_CLASS = System.getProperty("transformer.class.name",
            "org.codecrafterslab.data.impl.DefaultDataGuard");

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
        if (DEFAULT_DATA_GUARD_CLASS.equals(className.replace('/', '.'))) {
            log.info("transform className: {}", className);
            // TODO: 需要转换实现相应的转换逻辑，返回 null，表示不转换
            // 1. dump 类文件
            // 2. 修改类文件
        } else {
            log.trace("transform className: {}", className);
        }
        return null;
    }
}
