plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.forecast.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":features:forecast:domain"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":weather_utils"))

    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.bundles.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.lottie.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.android)
    implementation(libs.material3)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.ktx)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.androidx.junit.ktx)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.turbine)
}