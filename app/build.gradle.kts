plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "br.com.fernandorubini.teste_fernando_rubini"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.fernandorubini.teste_fernando_rubini"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {}
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
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
    implementation(fileTree("libs") {
        include("*.jar")
    })


    val kotlin_version = "1.9.0"
    val appcompat_version = "1.6.1"
    val core_ktx_version = "1.12.0"
    val constraintlayout_version = "2.1.4"
    val material_version = "1.11.0"
    val moshi_version = "1.15.1"
    val retrofit_version = "2.11.0"
    val okhttp_version = "4.10.0"
    val picasso_version = "2.71828"
    val circleimageview_version = "3.0.0"
    val gson_version = "2.10.1"
    val koin_version = "2.0.1"
    val lifecycle_version = "2.7.0"
    val rxjava_version = "2.2.17"
    val rxandroid_version = "2.1.1"
    val core_ktx_test_version = "1.2.0"
    val junit_version = "4.12"
    val mockito_version = "2.28.2"
    val mockk_version = "1.13.10"
    val mockito_kotlin_version = "2.1.0"
    val core_testing_version = "2.2.0"
    val coroutines_version = "1.7.3"
    val test_runner_version = "1.1.1"
    val espresso_version = "3.5.1"


    //core
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout_version")
    implementation("com.google.android.material:material:$material_version")

    //koin
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:koin_version")
    implementation("io.insert-koin:koin-androidx-viewmodel:$koin_version")
    implementation("io.insert-koin:koin-test:$koin_version")


    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-config")
    implementation ("com.google.firebase:firebase-storage")

    //jetpack
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //rxjava
    implementation("io.reactivex.rxjava2:rxjava:$rxjava_version")
    implementation("io.reactivex.rxjava2:rxandroid:$rxandroid_version")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")

    //okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:mockwebserver:$okhttp_version")

    //common
    implementation("com.squareup.picasso:picasso:$picasso_version")
    implementation("de.hdodenhof:circleimageview:$circleimageview_version")
    implementation("com.google.code.gson:gson:$gson_version")

    //test
    testImplementation("junit:junit:$junit_version")
    testImplementation("org.mockito:mockito-core:$mockito_version")
    testImplementation("io.mockk:mockk:$mockk_version")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockito_kotlin_version")
    testImplementation("androidx.arch.core:core-testing:$core_testing_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    androidTestImplementation("androidx.test:runner:$test_runner_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_version")
    androidTestImplementation("androidx.test:core-ktx:$core_ktx_test_version")
}
