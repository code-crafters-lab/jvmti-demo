public class TestApp {
    public static void main(String[] args) {
        System.out.println("启动测试应用...");
        try {
            byte[] encrypt = DataGuard.encrypt("HELLO WORLD".getBytes());
            System.out.println("加密结果：" + new String(encrypt));
            doWork();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void doWork() {
        System.out.println("执行工作...");
    }

    public static void premain(String[] args) {

    }
}
