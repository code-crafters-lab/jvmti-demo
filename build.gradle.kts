import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("ccl.lib")
    application
}

group = "org.codecrafterslab.unity"
version = "1.0-SNAPSHOT"


dependencies {
    testImplementation("com.grapecitysoft.documents:gcexcel:8.0.6")
}
val javaLibraryPath = "/Users/wuyujie/CLionProjects/jvmti-tools/install/lib"
val agentPath = "${javaLibraryPath}/libagent.dylib"

application {
    mainClass.set("TestApp")
    applicationDefaultJvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
        "-verbose:jni",
        "-agentlib:agent"
//        "-Djava.library.path=${javaLibraryPath}",
//        "-agentpath:${agentPath}"
    )
}

tasks {
    withType(JavaCompile::class.java) {
        options.release.set(8)
    }
    withType(KotlinCompile::class.java) {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    withType(Jar::class.java) {
//        manifest {
//            attributes["Premain-Class"] = "org.gradle.instrumentation.agent.Agent"
//            attributes["Agent-Class"] = "org.gradle.instrumentation.agent.Agent"
//            attributes["Can-Redefine-Classes"] = "true"
//            attributes["Can-Retransform-Classes"] = "true"
//        }
    }
}
