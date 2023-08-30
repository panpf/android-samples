plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
}

android {
    namespace = "com.github.panpf.android.compose.samples"
    compileSdk = property("compileSdk").toString().toInt()

    defaultConfig {
        applicationId = "com.github.panpf.android.compose.samples"

        minSdk = property("minSdk21").toString().toInt()
        targetSdk = property("targetSdk").toString().toInt()
        versionCode = property("versionCode").toString().toInt()
        versionName = property("versionName").toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.androidx.lifecycle.compose)
    implementation(libs.bundles.androidx.navigation.compose)
    implementation(libs.google.material)
    implementation(libs.panpf.tools4a)
    implementation(libs.panpf.tools4j)
    implementation(libs.panpf.tools4k)
    implementation(libs.panpf.sketch3)
    implementation(libs.panpf.sketch3.extensions)
    implementation(libs.panpf.zoomimage.compose.sketch)
    implementation(libs.panpf.zoomimage.compose.coil)
    implementation(libs.panpf.zoomimage.compose.glide)
    debugImplementation(libs.leakcanary)
    debugImplementation(libs.bundles.androidx.compose.debug)

    androidTestImplementation(libs.bundles.test)
}