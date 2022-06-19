plugins {
    java
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

dependencies {
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("jakarta.json.bind:jakarta.json.bind-api")
    implementation("jakarta.validation:jakarta.validation-api")

}