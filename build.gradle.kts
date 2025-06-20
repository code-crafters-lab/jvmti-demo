import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("ccl.bom")
}

group = "org.codecrafterslab"
version = "1.0.0-SNAPSHOT"

subprojects {
    tasks {
        /* 所有子项目均编译为 java 8 */
        withType(JavaCompile::class.java) {
            options.release.set(8)
        }
        withType(KotlinCompile::class.java) {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_1_8)
            }
        }
    }
}
