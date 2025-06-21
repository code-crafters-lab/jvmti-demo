# 保留主类和main方法
-keep public class com.example.MyApp {
    public static void main(java.lang.String[]);
}

# 保留序列化相关类
-keep class * implements java.io.Serializable { *; }

# 保留注解
-keepattributes *Annotation*

# 保留反射使用的类
-keep class com.example.reflect.** { *; }

# 禁用优化（可选，便于调试）

