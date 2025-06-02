public final class DataGuard {

    static {
        try {
            System.loadLibrary("data-guard");
        } catch (UnsatisfiedLinkError e) {
            // 可以考虑在这里添加备选方案或错误处理逻辑
            throw new ExceptionInInitializerError("无法加载数据保护模块，程序无法继续");
        }
    }

    /**
     * 加密数据
     *
     * @param plainText 明文字节数组
     * @return 密文字节数组
     * @throws Exception 异常
     */
    public native static byte[] encrypt(byte[] plainText) throws Exception;

    /**
     * 解密数据
     *
     * @param cipher 密文字节数组
     * @return 明文字节数组
     * @throws Exception 异常
     */
    public native static byte[] decrypt(byte[] cipher) throws Exception;


}
