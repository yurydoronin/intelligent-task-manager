plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common:types"))
    implementation(project(":core:domain"))

    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

}
