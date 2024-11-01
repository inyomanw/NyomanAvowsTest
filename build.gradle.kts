// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hiltLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinKapt) apply false
    alias(libs.plugins.kspLibrary) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}