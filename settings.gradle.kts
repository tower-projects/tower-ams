pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "tower-ams-k"

include("domain")

include("web")

include(
    "server:job",
    "server:notification"
)