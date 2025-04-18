pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherNow"
include(":app")
include(":core:domain")
include(":core:presentation")
include(":data")
include(":features:cityinput:domain")
include(":features:cityinput:presentation")
include(":features:currentweather:presentation")
include(":features:forecast:presentation")
include(":features:currentweather:domain")
include(":features:forecast:domain")

