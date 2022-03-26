plugins {
    java
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

dependencies {
    compileOnly("io.quarkus:quarkus-panache-common")
    compileOnly(project(":domain"))
}