package pro.jsandoval

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra

fun Project.addHelpers() {
    dependencies {
        add("implementation", "com.jakewharton.timber:timber:4.7.1")
        add("implementation", "com.google.code.gson:gson:2.8.5")
    }
}

fun Project.addKotlinCore() {
    val kotlinVersion = project.extra.get("versions.kotlin")
    dependencies {
        add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")
    }
}

fun Project.addAndroidCore() {
    dependencies {
        add("implementation", "androidx.multidex:multidex:2.0.1")
        add("implementation", "androidx.core:core-ktx:1.2.0")
        add("implementation", "androidx.appcompat:appcompat:1.1.0")
        add("implementation", "com.google.android.material:material:1.3.0-alpha02")
        add("implementation", "androidx.constraintlayout:constraintlayout:2.0.4")
        add("implementation", "androidx.activity:activity-ktx:1.1.0")
        add("implementation", "androidx.fragment:fragment-ktx:1.3.0-alpha04")
        add("implementation", "androidx.work:work-runtime-ktx:2.5.0")
    }
}

fun Project.addUiUtils() {
    dependencies {
        add("implementation", "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
        add("implementation", "io.coil-kt:coil:0.9.2")
        add("implementation", "com.google.android.exoplayer:exoplayer-core:2.12.0")
        add("implementation", "com.google.android.exoplayer:exoplayer-ui:2.12.0")
        add("implementation", "com.google.android.exoplayer:exoplayer-hls:2.12.0")
    }
}

fun Project.addNavigation() {
    val navigationVersion = extra["versions.navigation"]
    dependencies {
        add("implementation", "androidx.navigation:navigation-fragment-ktx:$navigationVersion")
        add("implementation", "androidx.navigation:navigation-ui-ktx:$navigationVersion")
    }
}

fun Project.addLifecycle() {
    val lifeCycleVersion = "2.2.0"
    dependencies {
        add("implementation", "androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion")
    }
}

fun Project.addRoom() {
    val roomVersion = "2.2.6"
    dependencies {
        add("implementation", "androidx.room:room-runtime:$roomVersion")
        add("implementation", "androidx.room:room-ktx:$roomVersion")
        add("kapt", "androidx.room:room-compiler:$roomVersion")
    }
}

fun Project.addHilt() {
    val hiltVersion = extra["versions.hilt"]
    val hiltJetpackVersion = "1.0.0-alpha01"
    dependencies {
        add("implementation", "com.google.dagger:hilt-android:$hiltVersion")
        add("kapt", "com.google.dagger:hilt-android-compiler:$hiltVersion")
        add("implementation", "androidx.hilt:hilt-lifecycle-viewmodel:$hiltJetpackVersion")
        add("implementation", "androidx.hilt:hilt-work:$hiltJetpackVersion")
        add("kapt", "androidx.hilt:hilt-compiler:$hiltJetpackVersion")
    }
}

fun Project.addNetworking() {
    val okHttpVersion = "4.9.0"
    val retrofitVersion = "2.9.0"
    dependencies {
        add("implementation", "com.squareup.okhttp3:okhttp:$okHttpVersion")
        add("implementation", "com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
        add("implementation", "com.squareup.retrofit2:retrofit:$retrofitVersion")
        add("implementation", "com.squareup.retrofit2:converter-gson:$retrofitVersion")
    }
}