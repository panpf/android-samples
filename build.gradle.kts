buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(libs.android.plugin)
        classpath(libs.androidx.navigation.safeargsgradle.plugin)
        classpath(libs.kotlin.plugin)
        classpath(libs.kotlin.serialization.plugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}