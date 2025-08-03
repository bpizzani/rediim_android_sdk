plugins {
    id("com.android.library") version "7.4.2"
    kotlin("android") version "1.9.0"
    id("maven-publish") // Needed for JitPack to generate .pom
}

android {
    namespace = "com.rediim.sdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    // Required for JitPack to publish the release variant
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Disable Dokka tasks defensively at configuration time
gradle.taskGraph.whenReady {
    tasks.filter { it.name.contains("dokka", ignoreCase = true) }.forEach { it.enabled = false }
}

// Must be after 'android' is fully configured
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.bpizzani"
                artifactId = "rediim_android_sdk"
                version = "1.1.4" // Must match the Git tag exactly
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

    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
    // Kotlin Serialization
    implementation 'org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3'

    // Optional: AndroidX KTX for cleaner Kotlin Android code
    implementation 'androidx.core:core-ktx:1.12.0'

}
