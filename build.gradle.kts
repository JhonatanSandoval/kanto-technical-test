// Top-level build file where you can add configuration options common to all sub-projects/modules.

tasks.named("wrapper", Wrapper::class.java) { distributionType = Wrapper.DistributionType.ALL }

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra.get("versions.kotlin")}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) { delete(rootProject.buildDir) }
