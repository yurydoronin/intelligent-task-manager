plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":core:application"))
    implementation(project(":core:domain"))
    implementation(project(":common:types"))

    implementation("org.springframework.boot:spring-boot-starter-web:4.0.5")

    implementation("tools.jackson.module:jackson-module-kotlin:3.1.0")

    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.32")
}

tasks.test {
    useJUnitPlatform()
}
