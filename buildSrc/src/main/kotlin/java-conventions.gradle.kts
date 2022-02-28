import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins.withType(JavaBasePlugin::class) {
    project.setProperty("sourceCompatibility", JavaVersion.VERSION_11)
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_11.majorVersion
    targetCompatibility = JavaVersion.VERSION_11.majorVersion
}

tasks.withType(Javadoc::class) {
    options {
        encoding("UTF-8").source(JavaVersion.VERSION_11.majorVersion)
        (this as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}

tasks.withType(KotlinCompile::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
    kotlinOptions.javaParameters = true
    sourceCompatibility = JavaVersion.VERSION_11.majorVersion
    targetCompatibility = JavaVersion.VERSION_11.majorVersion
}

plugins.withType(JavaPlugin::class) {
    val towerMessageVersion: String by project
    val quarkusVersion: String by project
    val enforced = dependencies.platform("io.iamcyw.tower:messaging-dependencies:$towerMessageVersion")
    val quarkusEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion")
    val camelEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-camel-bom:$quarkusVersion")

    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, enforced)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, quarkusEnf)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, camelEnf)
    configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME) {
        dependencies.add(quarkusEnf)
    }
}