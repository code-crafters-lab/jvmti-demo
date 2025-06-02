rootProject.name = "jvmti-demo"

pluginManagement {
    repositories {
        mavenLocal()
//        maven { url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public"); isAllowInsecureProtocol = true }
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
