import pro.jsandoval.addAndroidCore
import pro.jsandoval.addHelpers
import pro.jsandoval.addHilt
import pro.jsandoval.addKotlinCore
import pro.jsandoval.addLifecycle
import pro.jsandoval.addNavigation
import pro.jsandoval.addNetworking
import pro.jsandoval.addRoom
import pro.jsandoval.addUiUtils

plugins {
    id("com.android.application")
    id("plugins.common")
    id("plugins.flavors")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        applicationId = "pro.jsandoval.kantotest"
    }
    buildFeatures {
        dataBinding = true
    }
}

addAndroidCore()
addKotlinCore()
addHilt()
addNetworking()
addRoom()
addNavigation()
addLifecycle()
addHelpers()
addUiUtils()

dependencies {


}