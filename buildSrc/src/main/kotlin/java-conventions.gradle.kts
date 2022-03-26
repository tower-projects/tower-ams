import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins.withType(JavaBasePlugin::class) {
    project.setProperty("sourceCompatibility", JavaVersion.VERSION_17)
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_17.majorVersion
    targetCompatibility = JavaVersion.VERSION_17.majorVersion
}

tasks.withType(Javadoc::class) {
    options {
        encoding("UTF-8").source(JavaVersion.VERSION_17.majorVersion)
        (this as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}

tasks.withType(KotlinCompile::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.majorVersion
    kotlinOptions.javaParameters = true
    sourceCompatibility = JavaVersion.VERSION_17.majorVersion
    targetCompatibility = JavaVersion.VERSION_17.majorVersion
}

plugins.withType(JavaPlugin::class) {
    val enforced = dependencies.platform("io.iamcyw.tower:messaging-dependencies:${rootProject.extra["tower"]}")
    val quarkusEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-bom:${rootProject.extra["quarkus"]}")
    val camelEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-camel-bom:${rootProject.extra["quarkus"]}")

    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, enforced)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, quarkusEnf)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, camelEnf)
    configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME) {
        dependencies.add(quarkusEnf)
    }
}