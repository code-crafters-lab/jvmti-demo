plugins {
    id("ccl.lib")
    application
}

group = "org.codecrafterslab"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":data-guard"))
    runtimeOnly(project(":crack-agent"))
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("com.beust:jcommander:1.82")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

var javaLibraryPath = "/Users/wuyujie/CLionProjects/jvmti-tools/install/lib"
var agentPath = "${javaLibraryPath}/libagent.dylib"
if (System.getProperty("os.name").contains("Windows")) {
    javaLibraryPath = "D:\\project\\open-source\\jvmti-tools\\install\\bin"
    agentPath = "${javaLibraryPath}\\agent.dll"
}

enum class AgentType { JAVA, NATIVE }

var agentType = AgentType.JAVA
/* java agent */
val agentJar = project(":crack-agent").tasks.jar.get().outputs.files.singleFile
/* native agent */
val agentpath = "${javaLibraryPath}/agent.dll"

application {
    mainClass.set("org.codecrafterslab.App")
    val args = mutableListOf("-Dfile.encoding=UTF-8")

    if (agentJar.exists() && agentType == AgentType.JAVA) {
        args.add("-javaagent:${agentJar.absolutePath}")
    }

    if (agentpath.isNotEmpty() && agentType == AgentType.NATIVE) {
        args.add("-agentpath:${agentPath}")
    }

    applicationDefaultJvmArgs = args

    println(args)
}

