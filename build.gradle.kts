plugins {
    kotlin("plugin.serialization") version "1.6.10" apply false
    id("io.quarkus") version "2.7.2.Final" apply false
    id("org.kordamp.gradle.jandex") version "0.11.0" apply false
}

ext {
    // Dependencies
    set("tower", "1.2.0-SNAPSHOT")
    set("quarkus", "2.7.2.Final")
}

allprojects {
    repositories {
        maven("https://maven.aliyun.com/repository/central")
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.MINUTES)
    }
}

subprojects {
    group = "io.iamcyw.ams"
    version = "1.0-SNAPSHOT"
}