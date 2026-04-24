plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common:types"))
    implementation(project(":core:domain"))

    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

    // Jdbc
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:4.0.5")
}
