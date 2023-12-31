plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "org.freem"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)

    sourceSets {
        val main by getting {
            dependencies {
                implementation("org.bytedeco:llvm-platform:16.0.4-1.5.9")
            }
        }
        val test by getting
    }
}
