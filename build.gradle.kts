buildscript {
    repositories {
        maven { setUrl("https://repo.huaweicloud.com/repository/maven/") }  // central, google, jcenter
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
        maven { setUrl("https://repo.huaweicloud.com/repository/maven/") }  // central, google, jcenter
        mavenCentral()
        google()
        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}