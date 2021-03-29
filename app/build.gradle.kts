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

// or extra dependencies
dependencies {

    implementation(platform("com.google.firebase:firebase-bom:26.5.0"))
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.3")
}