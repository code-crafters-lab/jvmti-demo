plugins {
    id("java")
}

group = "org.codecrafterslab"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(
        fileTree(
            mapOf(
                "dir" to "/Applications/FineReport/webapps/webroot/WEB-INF/lib",
                "include" to listOf("*.jar")
            )
        )
    )

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
