import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.project.bonggong"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.project.bonggong"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "OPENAI_API_KEY", getApiKey("OPENAI_API_KEY"))
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            // proguard 활성화
            isMinifyEnabled = false

            // 기본 설정
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-rules-debug.pro"  // debug version에 대한 별도의 룰 적용
            )
        }
        release {
            // proguard 활성화
            isMinifyEnabled = false

            // 기본 설정
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

// local.properties에서 API key 가져오기
fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Glide 라이브러리 및 컴파일러 추가
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    // Navigation 라이브러리 추가
    implementation (libs.navigation.fragment.ktx)
    implementation (libs.navigation.ui.ktx)
}