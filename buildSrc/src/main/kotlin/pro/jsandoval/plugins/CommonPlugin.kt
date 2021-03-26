package pro.jsandoval.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            configureGeneral()
            configureAndroid()
        }
    }

    private fun Project.configureGeneral() {
        plugins.apply("kotlin-android")
        plugins.apply("kotlin-kapt")
        plugins.apply("kotlin-parcelize")
        plugins.apply("dagger.hilt.android.plugin")
        plugins.apply("androidx.navigation.safeargs.kotlin")

        tasks {
            withType(KotlinCompile::class.java) {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }
            }
        }
    }

    private fun Project.configureAndroid() {
        val androidExt = extensions["android"]
        if (androidExt is BaseExtension) {
            with(androidExt) {
                compileSdkVersion("${project.extra.get("versions.compileSdk")}".toInt())

                defaultConfig {
                    minSdkVersion("${project.extra.get("versions.minSdk")}")
                    targetSdkVersion("${project.extra.get("versions.targetSdk")}".toInt())

                    versionCode = project.extra.get("versions.code") as? Int
                    versionName = "${project.extra.get("versions.name")}"

                    buildConfigField("String", "USER_AGENT_APP_NAME", "\"KantoTest-AndroidApp\"")
                    buildConfigField("long", "VERSION_CODE", "${project.extra.get("versions.code")}")
                    buildConfigField("String", "VERSION_NAME", "\"${project.extra.get("versions.name")}\"")
                }

                compileOptions {
                    targetCompatibility = JavaVersion.VERSION_1_8
                    sourceCompatibility = JavaVersion.VERSION_1_8
                }

                buildTypes {
                    getByName("debug") {
                        versionNameSuffix = " DEV"
                        buildConfigField("String", "VERSION_NAME", "\"${project.extra.get("versions.name")} DEV\"")
                    }
                    getByName("release") {
                        setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-android.txt"))
                    }
                }
            }
        }
    }


}