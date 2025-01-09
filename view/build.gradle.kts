plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
}

android {
    namespace = "com.github.panpf.android.view.samples"
    compileSdk = property("compileSdk").toString().toInt()

    defaultConfig {
        applicationId = "com.github.panpf.android.view.samples"

        minSdk = property("minSdk").toString().toInt()
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
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.google.material)
    implementation(libs.panpf.tools4a)
    implementation(libs.panpf.tools4j)
    implementation(libs.panpf.tools4k)
    implementation(libs.panpf.sketch4.view)
    implementation(libs.panpf.sketch4.extensions.view)
    implementation(libs.panpf.assemblyadapter4)
    implementation(libs.panpf.zoomimage.view.sketch4)
    implementation(libs.panpf.zoomimage.view.coil3)
    implementation(libs.panpf.zoomimage.view.glide)
    implementation(libs.panpf.zoomimage.view.picasso)
    implementation(libs.subsamplingscaleimageview)
    implementation(libs.photoview)
    debugImplementation(libs.leakcanary)

    androidTestImplementation(libs.bundles.test)
}