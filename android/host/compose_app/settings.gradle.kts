pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
val storageUrl = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("/Users/aghny/Repositories/org/aghnyap/omni/flutter/module/wrapper/build/host/outputs/repo")
        }
        maven {
            url = uri("$storageUrl/download.flutter.io")
        }
    }
}

rootProject.name = "ComposeApp"
include(":app")
include(":module:greeting")
project(":module:greeting").projectDir = File(rootDir, "../../module/greeting")