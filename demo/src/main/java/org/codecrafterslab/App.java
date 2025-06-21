package org.codecrafterslab;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import org.codecrafterslab.data.DataGuard;

import java.nio.charset.StandardCharsets;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("应用启动......");
        Options options = new Options();
        JCommander jc = JCommander.newBuilder()
                .addObject(options)
                .build();
        try {
            jc.parse(args);
            if (options.isHelp()) {
                jc.usage();
                return;
            }
            // 业务逻辑
            doWork(options);

            // 模拟执行其他工作
            doOtherWork();
        } catch (Exception e) {
            log.error("参数错误: {}", e.getMessage());
            jc.usage();
            System.exit(1);
        }

    }

    private static void doWork(Options options) {
        // 1. 根据配置选择是否使用本地加密库
        boolean nativeEncrypted = options.isNativeEncrypted();
        if (nativeEncrypted) System.setProperty("data.guard.type","native");
        // 2. 获取具体实现
        DataGuard dataGuard = DataGuardContext.currentDataGuard();
        // 3. 确保其实现为 NativeDataGuard
        nativeEncrypted = dataGuard.isNative();
        // 4. 加密或解密
        if (options.getContent() == null) return;
        byte[] src = options.getContent().getBytes(StandardCharsets.UTF_8);
        byte[] dst;
        if (options.isEncrypted()) {
            dst = dataGuard.encrypt(src);
        } else {
            dst = dataGuard.decrypt(src);
        }
        String result = new String(dst, StandardCharsets.UTF_8);
        // 5. 打印结果
        log.info("执行{}{}：{} => {}", options.isEncrypted() ? "加密" : "解密",
                nativeEncrypted ? "(Native)" : "",
                options.getContent(), result);
    }

    private static void doOtherWork() {
        log.info("执行其他工作...");
        new Thread(() -> {
            for (int i = 1; i <=  3; i++) {
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
