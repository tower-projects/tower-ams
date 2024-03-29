plugins {
    java
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

dependencies {
    implementation("io.iamcyw.tower:messaging-core")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation(project(":domain"))
    implementation(project(":server:common"))
    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-scheduler")
    implementation("io.quarkus:quarkus-qute")
}