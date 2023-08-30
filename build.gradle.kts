plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.androidx.navigation.safeargs.kotlin) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}