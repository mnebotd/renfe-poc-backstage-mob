plugins {
    alias(coreLibs.plugins.android.application) apply false
    alias(coreLibs.plugins.android.library) apply false
    alias(coreLibs.plugins.kotlin.android) apply false
    alias(coreLibs.plugins.kotlin.compose) apply false
    alias(coreLibs.plugins.kotlin.serializable) apply false
    alias(coreLibs.plugins.google.ksp) apply false
    alias(coreLibs.plugins.google.hilt) apply false
    alias(coreLibs.plugins.google.crashlytics) apply false
    alias(coreLibs.plugins.dokka) apply false
    alias(coreLibs.plugins.detekt) apply false
    alias(libs.plugins.sonarqube) apply true
}

sonar {
    properties {
        property("sonar.login", System.getenv("SonarQubeRenfeProd"))
        property("sonar.host.url", "https://sonar.sir.renfe.es")
        property("sonar.projectKey", "ncr-android")
        property("sonar.projectName", "RenfeCercanias-Android")
        property("sonar.branch.name", System.getenv("GIT_BRANCH"))
    }
}
