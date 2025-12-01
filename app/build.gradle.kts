import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.perennial.weather"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.perennial.weather"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        val localProps = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(localProps.inputStream())
        val apiKey = properties.getProperty("OPEN_WEATHER_API_KEY") ?: ""
        val sqlCipherKey = properties.getProperty("MASTER_KEY_SQL_CIPHER") ?: ""

        buildConfigField("String", "OPEN_WEATHER_API_KEY", "\"$apiKey\"")
        buildConfigField("String", "MASTER_KEY_SQL_CIPHER", "\"$sqlCipherKey\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.compose)

    implementation(libs.play.services.location)

    implementation(libs.coil.compose)

    implementation(libs.androidx.security.crypto)

    implementation(libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite.ktx)

    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("androidx.test:core:1.6.0")
    testImplementation("androidx.test.ext:junit:1.2.1")
    testImplementation("app.cash.turbine:turbine:1.1.0")
}