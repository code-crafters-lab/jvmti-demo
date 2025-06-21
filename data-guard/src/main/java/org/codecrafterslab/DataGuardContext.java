package org.codecrafterslab;

import lombok.extern.slf4j.Slf4j;
import org.codecrafterslab.data.DataGuard;
import org.codecrafterslab.data.impl.DefaultDataGuard;
import org.codecrafterslab.data.impl.NativeDataGuard;

@Slf4j
public enum DataGuardContext {
    DEFAULT {
        @Override
        protected DataGuard create() {
            return new DefaultDataGuard();
        }
    },
    NATIVE {
        @Override
        protected DataGuard create() {
            return new NativeDataGuard();
        }
    };

    private static final String type;
    private static DataGuard dataGuard;

    protected abstract DataGuard create();

    public static DataGuard currentDataGuard() {
        if (dataGuard == null) {
            if (type.equals("Native")) {
                dataGuard = NATIVE.create();
            } else {
                dataGuard = DEFAULT.create();
            }
        }
        return dataGuard;
    }

    static {
        type = System.getProperty("data.guard.type", DEFAULT.name());
        if (NATIVE.name().equalsIgnoreCase(type)) {
            try {
                System.loadLibrary("data-guard");
            } catch (UnsatisfiedLinkError e) {
                // 可以考虑在这里添加备选方案或错误处理逻辑
                if (log.isWarnEnabled()) {
                    log.warn("无法加载本地数据保护模块，程序继续使用默认数据保护模块");
                }
            }
        }
    }
}
