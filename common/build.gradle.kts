plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
}

android {
    namespace = "com.github.panpf.android.samples.common"
    compileSdk = property("compileSdk").toString().toInt()

    defaultConfig {
        minSdk = property("minSdk").toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures{
        buildConfig = true
    }
    buildTypes {
        getByName("debug") {
            multiDexEnabled = true
        }
        getByName("release") {
            multiDexEnabled = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(libs.androidx.fragment)
    api(libs.androidx.appcompat)
    api(libs.bundles.androidx.lifecycle)
    api(libs.androidx.navigation.fragment)
    api(libs.androidx.navigation.ui)
    api(libs.androidx.paging.common)
    api(libs.google.material)
    api(libs.panpf.tools4a)
    api(libs.panpf.tools4j)
    api(libs.panpf.tools4k)
    api(libs.panpf.sketch4.core)
    api(libs.panpf.sketch4.http.okhttp)
    api(libs.panpf.sketch4.extensions.appicon)
    api(libs.coil3)
    api(libs.coil3.network.okhttp)

    debugImplementation(libs.leakcanary)
    androidTestImplementation(libs.bundles.test)
}