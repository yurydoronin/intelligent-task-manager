plugins {
    kotlin("jvm")

}

dependencies {
    implementation(project(":common:types"))

    // Arrow.Either
    implementation("io.arrow-kt:arrow-core:2.2.2.1")

    testImplementation(kotlin("test"))

}
