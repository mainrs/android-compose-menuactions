pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version("7.2.0")
        id("com.android.library") version("7.2.0")
        id("org.jetbrains.kotlin.android") version("1.6.10")
        id("nl.littlerobots.version-catalog-update") version("0.5.1")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-compose-menuactions"

include(":app")
include(":library")

enableFeaturePreview("VERSION_CATALOGS")
