plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "vn.edu.hou.mttha.demosqlite"
    compileSdk = 35

    defaultConfig {
        applicationId = "vn.edu.hou.mttha.demosqlite"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room Database
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0") // Nếu dùng Kotlin, cần kapt thay vì annotationProcessor
    implementation("androidx.room:room-ktx:2.5.0") // Hỗ trợ Coroutines với Room

    // LiveData & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Coroutines (Dùng để chạy các thao tác trên background thread)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    // Dagger/Hilt (Nếu bạn sử dụng Hilt cho Dependency Injection với Room)
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    annotationProcessor("androidx.hilt:hilt-compiler:1.0.0")

}