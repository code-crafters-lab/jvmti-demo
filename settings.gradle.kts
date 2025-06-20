rootProject.name = "jvmti-tools"

pluginManagement {
    repositories {
        mavenLocal()
        maven {
            url = uri("https://packages.aliyun.com/5f6a9b06d24814603933faab/maven/2038604-snapshot-xnrepo")
            credentials {
                username = "5f4ba059fa82bfeb805a1e09"
                password = "a3XkZLNApybs"
            }
        }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}

plugins {
    id("ccl.repo") version "0.10.0-beta.2"
}

include("data-gurad")
include("crack-agent")
include("demo")
include("other")
