buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Build.androidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.dokkaGradlePlugin)
        classpath(Build.ribbonizerPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
    }
}

tasks.register("clean").configure {
    delete("build")
}