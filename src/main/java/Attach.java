import com.sun.tools.attach.VirtualMachine;

public class Attach {
    public static void main(String[] args) {
        test();
//        new AgentBuilder.Default().type()

        // 1. 安装ByteBuddy代理
//        Instrumentation instrumentation = ByteBuddyAgent.install();
//        instrumentation.retransformClasses(MyClass.class);
        // 2. 定义字节码转换规则
//        ByteBuddy byteBuddy = new ByteBuddy();
//        License license = FRCoreContext.getLicense();
//        new ByteBuddy()
//                .type(ElementMatchers.is(BusinessService.class))
//                .method(ElementMatchers.named("processData"))
//                .transform((builder, typeDescription, classLoader, module) ->
//                        builder.visit(Advice.to(PerformanceMonitor.class).on(ElementMatchers.any()))
//                )
//                .make()
//                .load(
//                        BusinessService.class.getClassLoader(),
//                        ClassReloadingStrategy.fromInstalledAgent()
//                );

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
            vm.loadAgentPath(agentPath,null);
//            vm.loadAgentLibrary("agent", null);

            // 断开连接
            vm.detach();
            System.out.println("Agent 已成功附加到目标 JVM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
