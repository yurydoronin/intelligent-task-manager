import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.3.21" apply false
    kotlin("plugin.spring") version "2.3.21" apply false
    kotlin("plugin.jpa") version "2.3.21" apply false
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    group = "task.manager"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(25)
    }
}

tasks.register("printSrc") {
    doLast {
        subprojects.forEach { project ->
            val srcDir = project.file("src/main/kotlin")

            if (srcDir.exists()) {
                println("== ${project.path} ==")

                srcDir.walkTopDown()
                    .filter { it.isFile }
                    .forEach {
                        println(it.relativeTo(srcDir))
                    }
            }
        }
    }
}