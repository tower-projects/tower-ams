plugins {
    kotlin("js")
}

kotlin {
    js(IR) {
        browser()
    }.binaries.executable()

    dependencies {
        implementation(project(":domain"))

        val ui5Version = rootProject.extra["ui5Version"].toString()

        implementation(npm("@ui5/webcomponents", ui5Version))
        implementation(npm("@ui5/webcomponents-fiori", ui5Version))
        implementation(npm("@ui5/webcomponents-icons", ui5Version))
        implementation(npm("@ui5/webcomponents-theming", ui5Version))


        implementation("dev.fritz2:components:${rootProject.extra["fritz2Version"]}")
        implementation("dev.fritz2:styling:${rootProject.extra["fritz2Version"]}")
    }

}