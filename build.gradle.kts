allprojects {
    group = "io.iamcyw.ams"

    repositories {
//        maven("https://maven.aliyun.com/repository/central")
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")

    }

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.MINUTES)
    }
}