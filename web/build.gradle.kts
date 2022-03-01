plugins {
    kotlin("js")
}

val fritz2Version :String by project
val ui5Version :String by project
kotlin {
    js(IR) {
        browser()
    }.binaries.executable()

    dependencies {
        implementation(project(":domain"))

        implementation(npm("@ui5/webcomponents", ui5Version))
        implementation(npm("@ui5/webcomponents-fiori", ui5Version))
        implementation(npm("@ui5/webcomponents-icons", ui5Version))
        implementation(npm("@ui5/webcomponents-theming", ui5Version))


        implementation("dev.fritz2:components:$fritz2Version")
        implementation("dev.fritz2:styling:$fritz2Version")
    }

}