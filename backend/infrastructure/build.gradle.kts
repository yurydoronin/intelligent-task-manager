plugins {
    application
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation(project(":core:application"))
    implementation(project(":core:domain"))
    implementation(project(":common:types"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.zalando:logbook-ktor:3.12.3")

    // DB
    implementation("org.postgresql:postgresql:42.7.3")

    // Flyway
    implementation("org.flywaydb:flyway-core:10.10.0")

    // LLM
    implementation("dev.langchain4j:langchain4j:1.9.1")
    implementation("dev.langchain4j:langchain4j-open-ai:1.9.1")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.14.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.13.4")
    testImplementation("io.kotest:kotest-assertions-arrow-jvm:6.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("com.ninja-squad:springmockk:5.0.1")
}
