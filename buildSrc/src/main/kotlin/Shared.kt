import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

fun Project.allopen() {
    plugins.apply("org.jetbrains.kotlin.plugin.allopen")

//    extensions.getByType<allop>().apply {
//        annotation("javax.persistence.Entity")
//        annotation("javax.enterprise.context.ApplicationScoped")
//        annotation("io.quarkus.test.junit.QuarkusTest")
//    }

}