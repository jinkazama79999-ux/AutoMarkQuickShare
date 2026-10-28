plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.automark.quickshare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.automark.quickshare"
        minSdk = 26 // Android 8.0 Oreo (Oppo Reno 7, seri lama, hingga Android 15 terbaru)
        targetSdk = 35 // Android 15 (Oppo Reno 13, Reno 14 5G, OneUI 7, HyperOS)
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    val media3Version = "1.5.0"
    val coroutinesVersion = "1.9.0"

    // Core AndroidX, Lifecycle Service & DataStore Preferences
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-service:2.8.7")
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Jetpack Compose (Material 3) untuk UI Custom Template Builder
    implementation(platform("androidx.compose:compose-bom:2024.10.00"))
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Coroutines untuk Asynchronous IO, Debouncing, dan Queue Processing
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")

    // AndroidX ExifInterface untuk duplikasi metadata EXIF & ekstraksi GPS
    implementation("androidx.exifinterface:exifinterface:1.3.7")

    // AndroidX Media3 Transformer & Effect untuk Hardware-Accelerated Video Watermarking (30/60 FPS)
    implementation("androidx.media3:media3-transformer:$media3Version")
    implementation("androidx.media3:media3-effect:$media3Version")
    implementation("androidx.media3:media3-common:$media3Version")
    implementation("androidx.media3:media3-muxer:$media3Version")

    // Google Play Services Location untuk fallback Last Known Location secara pasif/offline
    implementation("com.google.android.gms:play-services-location:21.3.0")

    // SQLite KTX untuk Offline Reverse Geocoding Database
    implementation("androidx.sqlite:sqlite-ktx:2.4.0")
    implementation("androidx.sqlite:sqlite-framework:2.4.0")
}

