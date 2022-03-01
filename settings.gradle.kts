pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    val quarkusVersion: String by settings
    val kotlinVersion: String by settings
    plugins {
        id ("io.quarkus") version quarkusVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("org.kordamp.gradle.jandex") version "0.11.0"
    }
}
rootProject.name = "tower-ams-k"

include("web")
include("domain")

include(
    "server",
    "server:job",
    "server:notification"
)
