plugins {
    id("com.android.library") version "7.4.2"
    kotlin("android") version "1.9.0"
    `maven-publish`
}

android {
    namespace = "com.rediim.sdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.bpizzani"
                artifactId = "rediim_android_sdk"
                version = "1.0.2" // must match your tag name (see below)
            }
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.github.thumbmarkjs:thumbmark-android:1.0.+")
}
