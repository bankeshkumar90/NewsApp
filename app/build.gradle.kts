plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jillestchuh.ktlint)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.bk.indiatimes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bk.indiatimes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.bk.indiatimes"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.lifecycleViewmodelKtx)
    implementation(libs.lifecycle.runtime.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidTestExtJunit)
    androidTestImplementation(libs.androidTestEspressoCore)

    // hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    // Navigation
    implementation(libs.hiltNavigationCompose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.loggingInterceptor)

    // coroutine
    implementation(libs.kotlinxCoroutinesAndroid)
    implementation(libs.kotlinxCoroutinesCore)

    // Room
    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.roomKtx)

    // compose
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.activityCompose)
    debugImplementation(libs.composeUiTooling)

    implementation(libs.runtimeLivedata)

    // Coil
    implementation(libs.coilCompose)

}