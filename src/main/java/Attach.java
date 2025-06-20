import com.sun.tools.attach.VirtualMachine;

public class Attach {
    public static void main(String[] args) {
        test();
//        new AgentBuilder.Default().type()
    }

    private static void test() {
        try {
            // 获取目标 JVM 的进程 ID (PID)
            String targetPid = "22711";
            // 指定要附加的 Agent JAR 路径
            String agentPath = "/Users/wuyujie/Project/opensource/jvmti-demo/libagent.dylib";

            // 附加到目标 JVM
            VirtualMachine vm = VirtualMachine.attach(targetPid);
            // 加载 java agent
//            vm.loadAgent(agentPath,null);
            // 加载本地代理服务
            vm.loadAgentPath(agentPath, null);
//            vm.loadAgentLibrary("agent", null);

            // 断开连接
            vm.detach();
            System.out.println("Agent 已成功附加到目标 JVM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
