import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.masecla22:Reddit4J:2fb38684f6")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}