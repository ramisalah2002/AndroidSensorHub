plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.javaappcai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.javaappcai"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.google.android.material:material:1.4.0")

    implementation(libs.firebase.firestore)
    implementation(libs.camera.core)

    // Exclude Guava's ListenableFuture from Firebase Crashlytics
    implementation(libs.firebase.crashlytics.buildtools) {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }

    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
