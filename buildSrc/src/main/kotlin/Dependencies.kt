const val kotlinVersion = "1.4.10"

object Build {

    object Versions {
        const val gradle = "4.1.0"
        const val dokka = "0.9.17"
        const val ribbonizer = "2.0.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val dokkaGradlePlugin = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    const val ribbonizerPlugin = "com.github.gfx.ribbonizer:ribbonizer-plugin:${Versions.ribbonizer}"

}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val dokka = "org.jetbrains.dokka"
    const val ribbonizer = "com.github.gfx.ribbonizer"
    const val kotlinKapt = "kotlin-kapt"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

// librarias
object Libraries {
    // kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrow}"
    const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"
    // androidx
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    // dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    // google
    const val googleMaterial = "com.google.android.material:material:${Versions.googleMaterial}"
    // leakCanary
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    // retrofit
    const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    // testing
    const val junit = "junit:junit:${Versions.junit}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"

    private object Versions {
        // core & kotlin
        const val coroutines = "1.3.9"
        const val coroutinesAdapter = "0.9.2"
        // androidx
        const val appCompat = "1.2.0"
        const val lifecycle = "2.3.0-alpha07"
        const val constraintLayout = "2.0.1"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val swipeRefreshLayout = "1.1.0"
        // 3rd party
        const val leakCanary = "2.2"
        const val dagger = "2.28"
        const val glide = "4.11.0"
        const val googleMaterial = "1.1.0-alpha08"
        const val arrow = "0.11.0"
        const val retrofit = "2.8.1"
        const val okhttp = "4.2.1"
        const val moshi = "1.9.3"
        // test
        const val junit = "4.13"
        const val mockitoAndroid = "3.2.4"
        const val mockitoKotlin = "2.1.0"
    }
}