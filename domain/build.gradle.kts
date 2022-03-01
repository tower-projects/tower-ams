plugins {
    kotlin("multiplatform")
//    kotlin("plugin.serialization")
    id("org.kordamp.gradle.jandex")
    // KSP support
    id("com.google.devtools.ksp") version "1.6.10-1.0.4"
}
val fritz2Version: String by project

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
            kotlin.srcDir("build/generated/ksp/commonMain/kotlin")
            dependencies {
                val serializationVersion: String by project
                implementation(kotlin("stdlib-common"))
                implementation("dev.fritz2:core:$fritz2Version")
//                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val quarkusVersion: String by project
        val jvmMain by getting {
            dependencies {
//                api("io.quarkus:quarkus-hibernate-orm-panache-kotlin:$quarkusVersion")
            }
        }
    }
}

/**
 * KSP support - start
 */
dependencies {
    add("kspMetadata", "dev.fritz2:lenses-annotation-processor:$fritz2Version")
}
//project.kotlin.sourceSets.commonMain {
//    kotlin.srcDir("build/generated/ksp/commonMain/kotlin")
//}
//kotlin.sourceSets.commonMain { kotlin.srcDir("/domain/build/generated/ksp/metadata/commonMain/kotlin") }
tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspKotlinMetadata") dependsOn("kspKotlinMetadata")
}
//needed to work on Apple Silicon . Should be fixed by 1.6.20(
//    https://youtrack.jetbrains.com/issue/KT-49109#focus=Comments-27-5259190.0-0)
//    rootProject. plugins . withType < org . jetbrains . kotlin . gradle . targets . js . nodejs . NodeJsRootPlugin > {
//    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion = "16.0.0"
//}
/**
 * KSP support - end
 */