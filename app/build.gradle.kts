import org.jetbrains.dokka.gradle.DokkaTask
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

plugins {
    id(Plugins.androidApplication)

    id(Plugins.kotlinAndroid)
    // add automatic documentation generator feature
    id(Plugins.dokka)
    // add overlaid launcher icons feature
    id(Plugins.ribbonizer)
}

val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream("keystore.properties"))

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "org.deafsapps.android.githubbrowser"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        multiDexEnabled  = true
    }
    // add version management feature
    generateAppVersioning()
    signingConfigs {
        register("release") {
            storeFile = keystoreProperties["storeFile"]?.let { file(it) }
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
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
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
        skipEmptyPackages = true   // skip empty packages
        skipDeprecated = true   // skip deprecated
        noStdlibLink = true   // skip documentation related to kotlin-stdlib
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.lifecycle)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.cardview)
    // other modules
    implementation(project(":presentation-layer"))
    implementation(project(":domain-layer"))
//    implementation(project(":data-layer"))
    // 3rd party libraries
    debugImplementation(Libraries.leakCanary)
}

fun generateAppVersioning() {
    val versionPropertiesFile = rootProject.file("version.properties")

    if (versionPropertiesFile.canRead()) {
        val versionProperties = Properties()
        versionProperties.load(FileInputStream(versionPropertiesFile))

        val versionMajor = versionProperties["VERSION_MAJOR"] as? Int ?: 0
        val versionMinor = versionProperties["VERSION_MINOR"] as? Int ?: 1
        var buildNumber = versionProperties["BUILD_NUMBER"] as? Int ?: 1

        android {
            // increment 'buildNumber' in case a release is compiled
            val runTasks = gradle.startParameter.taskNames
            runTasks.forEach { task ->
                if (task.contains("assembleRelease")) {
                    // Update the build number in the local file
                    buildNumber++
                    versionProperties["BUILD_NUMBER"] = (buildNumber).toString()
                    versionProperties.store(FileOutputStream(versionPropertiesFile), null)
                }
            }

            defaultConfig {
                println("Build number: $buildNumber")
                versionCode = buildNumber
                versionName = "${versionMajor}.${versionMinor}.${buildNumber}"
            }
        }
    } else {
        throw GradleException("Could not read 'version.properties' from project root")
    }
}