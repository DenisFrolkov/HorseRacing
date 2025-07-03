plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("org.jetbrains.kotlin.kapt")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    //Dagger 2
    implementation("com.google.dagger:dagger:2.56.2")
    kapt("com.google.dagger:dagger-compiler:2.56.2")
}
