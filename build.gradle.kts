plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    idea
}

group = "me.fortibrine"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()

        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.google.devtools.ksp")
    }

    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            targetCompatibility = "1.8"
            sourceCompatibility = "1.8"
        }

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    dependencies {
        val libs = rootProject.libs
    }
}
