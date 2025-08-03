plugins {
    id("com.android.library") version "7.4.2"
    kotlin("android") version "1.9.0"
    id("maven-publish") // Needed for JitPack to generate .pom
}

android {
    namespace = "com.rediim.sdk"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["release"])
            groupId = "com.github.bpizzani"
            artifactId = "rediim_android_sdk"
            version = "1.0.7" // match the Git tag exactly!
        }
    }
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.github.thumbmarkjs:thumbmark-android:1.0.+")
}
