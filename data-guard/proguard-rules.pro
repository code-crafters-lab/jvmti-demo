# 保留枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留 SLF4J
-keep interface org.slf4j.** { *; }
-keep class org.slf4j.impl.** { *; }

# 保留 DataGuard 接口定义
-keep interface org.codecrafterslab.data.DataGuard {
    public *;
}

# 保留序列化相关类
-keep class * implements java.io.Serializable { *; }

# 保留注解
-keepattributes *Annotation*

# 保留反射使用的类
-keep class com.example.reflect.** { *; }
