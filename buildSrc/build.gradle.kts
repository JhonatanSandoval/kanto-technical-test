import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
apply(from = "properties.gradle.kts")

gradlePlugin {
    plugins {
        register("common-plugin") {
            id ="plugins.common"
            implementationClass = "pro.jsandoval.plugins.CommonPlugin"
        }
        register("flavors-plugin") {
            id ="plugins.flavors"
            implementationClass = "pro.jsandoval.plugins.FlavorsPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

tasks {
    withType(JavaCompile::class.java) {
        options.compilerArgs = listOf("-Xlint:deprecation", "-Xlint:unchecked")
    }
}

// Classpath dependencies
dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:${project.extra.get("versions.androidGradlePlugin")}")
    implementation("com.android.tools.build:gradle-api:${project.extra.get("versions.androidGradlePlugin")}")
    implementation(kotlin("gradle-plugin", "${project.extra.get("versions.kotlin")}"))
    implementation("com.google.dagger:hilt-android-gradle-plugin:${project.extra.get("versions.hilt")}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${project.extra.get("versions.navigation")}")
}