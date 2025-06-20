plugins {
    id("ccl.lib")
    application
}

group = "org.codecrafterslab"

dependencies {
//    api(project(":data-guard"))
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.18")

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

application {
    mainClass.set("org.codecrafterslab.App")
    applicationDefaultJvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
//        "-verbose:jni",
//        "-agentlib:agent"
//        "-Djava.library.path=${javaLibraryPath}",
//        "-agentpath:${agentPath}"
    )
}
