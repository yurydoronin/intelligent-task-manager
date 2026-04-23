plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":core:application"))
    implementation(project(":core:domain"))
    implementation(project(":common:types"))


    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.zalando:logbook-ktor:3.12.3")
}
