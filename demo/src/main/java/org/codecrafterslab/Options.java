package org.codecrafterslab;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class Options {

    @Parameter(names = {"-h", "--help"}, description = "显示帮助信息", help = true)
    private boolean help;

    @Parameter(names = {"-e", "--encrypt"}, description = "是否加密，默认解密")
    private boolean encrypted;

    @Parameter(names = {"-n", "--native"}, description = "是否使用本地加密实现")
    private boolean nativeEncrypted;

    @Parameter(names = {"-c", "--content"}, description = "加密或解密文本")
    private String content;
}
