apply(from = "./buildScripts/install-git-hooks.gradle.kts")
apply(plugin = "kover")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.ktlint)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

kover {
    isDisabled.set(false)
    engine.set(kotlinx.kover.api.DefaultIntellijEngine)
}

koverMerged {
    enable()
    htmlReport {
        reportDir.set(layout.buildDirectory.dir("kover-report/html-report"))
        filters {
            projects {
                excludes += listOf(":androidTest-shared")
            }
            classes {
                excludes += listOf(
                    "jdk.internal.*",
                    "dagger.hilt.internal.aggregatedroot.codegen.**",
                    "hilt_aggregated_deps.**",
                    "dev.mslalith.focuslauncher.**.*_Factory"
                )
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
