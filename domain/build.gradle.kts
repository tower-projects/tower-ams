plugins {
    `kotlin-multiplatform`
}

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.majorVersion
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }
    }
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("dev.fritz2:core:${rootProject.ext["fritz2Version"]}")
            }
        }
    }
}