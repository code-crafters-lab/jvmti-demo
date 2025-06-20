package org.codecrafterslab;

import org.codecrafterslab.data.DataGuard;
import org.codecrafterslab.data.impl.DefaultDataGuard;
import org.codecrafterslab.data.impl.NativeDataGuard;

public enum DataGuardContext {
    DEFAULT {
        @Override
        protected DataGuard create() {
            return new DefaultDataGuard();
        }
    },
    Native {
        @Override
        protected DataGuard create() {
            return new NativeDataGuard();
        }
    };

    private static String type = DEFAULT.name();

    protected abstract DataGuard create();

    public static DataGuardContext currentDataGuard() {
        return DEFAULT;
    }

    static {
        type = System.getProperty("data.guard.type", DEFAULT.name());
        if (Native.name().equalsIgnoreCase(type)) {
            try {
                System.loadLibrary("data-guard");
            } catch (UnsatisfiedLinkError e) {
                // 可以考虑在这里添加备选方案或错误处理逻辑
                throw new ExceptionInInitializerError("无法加载数据保护模块，程序无法继续");
            }
        }
    }
}
