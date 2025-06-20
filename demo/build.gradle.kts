plugins {
    id("ccl.lib")
//    applica
}
group = "org.codecrafterslab"

repositories {
    mavenCentral()
}

dependencies {
//    implementation(project(":data-guard"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}