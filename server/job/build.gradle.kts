plugins {
    java
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

dependencies {
    implementation("io.iamcyw.tower:messaging-core")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("org.eclipse.microprofile.graphql:microprofile-graphql-api:1.1.0")

    implementation(project(":domain"))
    implementation(project(":server:common"))

    implementation("org.mvel:mvel2:2.4.14.Final")


}