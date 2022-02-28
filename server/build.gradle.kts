plugins {
    kotlin("jvm")
    id("io.quarkus")
    id("java-conventions")
}


val serializationVersion:String by project
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")
    // 为了序列化来自kotlin的domain
    implementation("io.quarkus:quarkus-kotlin")

    implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-jdbc-oracle")
    implementation("io.quarkus:quarkus-vertx-web")
    implementation("io.iamcyw.tower:messaging-core")
    implementation("io.iamcyw.tower:tower-quarkus")
    implementation("io.iamcyw.tower:tower-quarkus-deployment")

    implementation("org.apache.camel.quarkus:camel-quarkus-core")

    testImplementation("io.quarkus:quarkus-junit5")

//    implementation(project(":server:job"))
//    implementation(project(":server:notification"))

    implementation(project(":domain"))
}