plugins {
    java
    id("io.quarkus")
    id("java-conventions")
}


dependencies {
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-jdbc-oracle")
    implementation("io.quarkus:quarkus-vertx-web")
    implementation("io.quarkus:quarkus-smallrye-graphql")

    implementation("io.iamcyw.tower:messaging-core")
    implementation("io.iamcyw.tower:tower-quarkus")
    implementation("io.iamcyw.tower:tower-quarkus-deployment")

    implementation("org.apache.camel.quarkus:camel-quarkus-core")

    testImplementation("io.quarkus:quarkus-junit5")

    implementation(project(":server:job"))
    implementation(project(":server:notification"))

    implementation(project(":domain"))
}