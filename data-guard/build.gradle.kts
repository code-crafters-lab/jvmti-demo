buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.7.0")
    }
}
plugins {
    id("ccl.lib")
}

group = "org.codecrafterslab.data"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")
//    runtimeOnly("com.guardsquare:proguard-gradle:7.7.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.5.18")

    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<proguard.gradle.ProGuardTask>("proguardJar") {
    group = "build"
    description = "Obfuscate JAR using ProGuard"

    dependsOn(tasks.build)
    // 输入：原始JAR文件
    injars(tasks.jar.get().outputs.files.singleFile)

    // 输出：混淆后的JAR文件
    outjars(layout.buildDirectory.file("libs/${project.name}-obfuscated.jar"))

    // 指定 JDK 模块路径
    libraryjars("/Users/wuyujie/Library/Java/JavaVirtualMachines/ms-21.0.7/Contents/Home/jmods/java.base.jmod")
    // 库依赖（用于解析类引用）
    libraryjars(configurations.runtimeClasspath.get().asFileTree.files)

    // 应用ProGuard配置
    configuration("proguard-rules.pro")

    // 可选：生成映射文件（用于调试混淆后的代码）
    printmapping(layout.buildDirectory.file("outputs/mapping/proguard/mapping.txt"))

    // 可选：优化级别
    optimizationpasses(5)

    // 可选：调试信息
    verbose()


//    keep("-keep class org.codecrafterslab.DataGuardContext { *; }")
    // 添加 Lombok 注解的保留规则, 忽略找不到的 Lombok 注解
    dontwarn("lombok.Generated")
}
