plugins {
    alias(coreLibs.plugins.core.presentation)
}

android {
    namespace = "com.core.presentation"
    kotlin.compilerOptions.freeCompilerArgs.add("-Xannotation-default-target=param-property")
}

dependencies {}
