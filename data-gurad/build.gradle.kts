plugins {
    id("ccl.lib")
}

group = "org.codecrafterslab.data"

dependencies {
//    implementation("org.codecrafterslab:lib:1.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}