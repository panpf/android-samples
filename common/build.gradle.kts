plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.google.material)
    implementation(libs.panpf.tools4a)
    implementation(libs.panpf.tools4j)
    implementation(libs.panpf.tools4k)
    implementation(libs.panpf.sketch3)
    debugImplementation(libs.leakcanary)

    androidTestImplementation(libs.bundles.test)
}