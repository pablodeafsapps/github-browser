plugins {
    id(Plugins.androidLibrary)

    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    // add automatic documentation generator feature
    id(Plugins.dokka)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
        getByName("test") { java.srcDir("src/test/kotlin") }
    }
    lintOptions {
        isAbortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks {
    val dokka by getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
        skipEmptyPackages = true   // skip empty packages
        skipDeprecated = true   // skip deprecated
        noStdlibLink = true   // skip documentation related to kotlin-stdlib
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.kotlinCoroutinesAndroid)
    // 3rd party libraries
    api(Libraries.arrowCore)
    api(Libraries.arrowSyntax)
    kapt(Libraries.arrowMeta)
    api(Libraries.retrofit)
    api(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    // testing dependencies - Unit Test
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockitoKotlin)
    testImplementation(Libraries.kotlinCoroutinesTest)
}