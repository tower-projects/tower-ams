plugins {
    id("java")
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

dependencies {
    implementation("io.quarkus:quarkus-hibernate-orm-panache")

    implementation("io.iamcyw.tower:messaging-core")

    implementation(project(":domain:job-domain"))
    implementation(project(":domain:notification-domain"))

}