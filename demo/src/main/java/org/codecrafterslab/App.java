package org.codecrafterslab;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    private static boolean encypted = false;
    private static boolean _native = false;

    public static void main(String[] args) {
        log.info("应用启动，参数数量：{}", args.length);

        // 逐个打印参数
        for (int i = 0; i < args.length; i++) {
            log.debug("参数[{}]: {}", i, args[i]);
        }

        // 根据参数设置加密和本地库标志
        doWork(encypted, "1234567890", _native);

        // 其他工作
        doOtherWork();
    }

    private static void doWork(boolean encypted, String number, boolean aNative) {
        log.info("执行工作，加密：{}，数字：{}，是否使用本地库：{}", encypted, number, aNative);
    }

    private static void doOtherWork() {
        log.info("执行其他工作...");
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                log.debug("工作线程执行中 {}...", i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    if (log.isErrorEnabled()) {
                        log.error("工作线程执行出错：", e);
                    }
                }
            }
        }).start();
    }
}
