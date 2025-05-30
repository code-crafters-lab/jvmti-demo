plugins {
    java
    kotlin("jvm") version "2.1.20"
}

group = "org.codecrafterslab.unity"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
//    jvmToolchain(8)
}