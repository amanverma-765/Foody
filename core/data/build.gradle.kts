import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlinx.serialization)
}

android {
    namespace = "org.techinowave.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField(
            type ="String",
            name = "supabaseUrl",
            value = properties.getProperty("supabaseUrl")
        )
        buildConfigField(
            type ="String",
            name = "supabaseApiKey",
            value = properties.getProperty("supabaseApiKey")
        )
    }
    buildFeatures {
        buildConfig = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Koin - DI
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.core.coroutines)
    implementation(libs.koin.androidx.compose)
    // Supabase
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.postgrest.kt)
    // Ktor
    implementation(libs.ktor.client.cio)
    // Ktx - Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":core:domain"))
    implementation(project(":core:utils"))
}