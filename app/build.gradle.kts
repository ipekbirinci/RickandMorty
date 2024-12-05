import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias (libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rickandmorty"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }



    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}



dependencies {

    implementation(libs.hilt.navigation.fragment)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.hilt.compiler)
    implementation(libs.androidx.lifecycle.viewmodel)
    kapt (libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.databinding.compiler)
    implementation(libs.viewbinding)

    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.lottie)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.glide)
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}