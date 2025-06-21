import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("ccl.lib")
    application
}

group = "org.codecrafterslab.agent"

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.17")
//    implementation("net.bytebuddy:byte-buddy:1.17.5")
//    implementation("net.bytebuddy:byte-buddy-agent:1.17.5")
    implementation(files("/Users/wuyujie/Library/Java/JavaVirtualMachines/corretto-1.8.0_452/Contents/Home/lib/tools.jar"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.5.18")

    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Title"] = "Gradle Java Agent Proxy"
        attributes["Implementation-Version"] = project.version
        attributes["Built-By"] = "coffee377"
        attributes["Built-Date"] = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss.SSS"))
        attributes["Built-Jdk"] = System.getProperty("java.version")
        attributes["Premain-Class"] = "org.codecrafterslab.agent.Agent"
        attributes["Agent-Class"] = "org.codecrafterslab.agent.Agent"
        attributes["Can-Redefine-Classes"] = true // 是否能重定义类
        attributes["Can-Retransform-Classes"] = true  // 是否能重转换类
        attributes["Can-Set-Native-Method-Prefix"] = true
    }
}
