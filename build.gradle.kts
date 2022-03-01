plugins {
//    kotlin("multiplatform") version "1.6.10" apply false
    kotlin("plugin.serialization") version "1.6.10" apply false
    id("com.google.devtools.ksp") version "1.6.10-1.0.2" apply false
    id("io.quarkus") version "2.7.2.Final" apply false
    id("org.kordamp.gradle.jandex") version "0.11.0" apply false
}

ext {
    // Dependencies
    set("kotlinVersion", "1.6.10")
    set("coroutinesVersion", "1.6.0")
    set("kotlinpoetVersion", "1.10.2")
    set("compileTestingVersion", "1.4.7")
    set("logbackVersion", "1.2.1")
    set("ktorVersion", "1.6.4") // upgrade to 1.6.5 produces test errors
    set("serializationVersion", "1.3.1")
    set("kspVersion", "1.6.10-1.0.2")
    set("autoServiceVersion", "1.0.1")
    set("junitJupiterParamsVersion", "5.8.1")
    set("assertJVersion", "3.19.0")
    set("fritz2Version", "0.14")
    set("towerVersion", "1.1.3-SNAPSHOT")
    set("ui5Version", "1.2.0")
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