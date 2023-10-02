plugins {
    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
    kotlin("android")
}

android {
    namespace = "com.excavator.authomator1"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.excavator.authomator1"
        minSdk = 18
        targetSdk = 33
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    sourceSets {
        getByName("androidTest") {
            java.srcDir("src/main/java")
        }
    }
}

dependencies {
    implementation("androidx.test:core:1.5.0")
    implementation("androidx.test:runner:1.5.2")
    implementation("androidx.test:rules:1.5.0")
    implementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.test.ext:truth:1.5.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("androidx.test.uiautomator:uiautomator:2.3.0-alpha04")
}