package pro.jsandoval.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

class FlavorsPlugin : Plugin<Project> {

    private val dimensionName = "environment"

    override fun apply(project: Project) {
        with(project) {
            val androidExt = extensions["android"]
            if (androidExt is BaseExtension) {
                with(androidExt) {
                    flavorDimensions(dimensionName)
                    productFlavors {
                        create("dev") {
                            dimension(dimensionName)
                            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/\"")
                        }
                        create("prod") {
                            dimension(dimensionName)
                            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/\"")
                        }
                    }
                }
            }
        }
    }
}