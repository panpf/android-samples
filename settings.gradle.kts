rootProject.name = "android-samples"

include(":common")
include(":compose")
include(":view")

pluginManagement {
    repositories {
//        maven { setUrl("https://repo.huaweicloud.com/repository/maven/") }
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven { setUrl("https://repo.huaweicloud.com/repository/maven/") }
        mavenCentral()
//        maven { setUrl("https://www.jitpack.io") }
        google()
//        maven { setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots") }
//        mavenLocal()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}