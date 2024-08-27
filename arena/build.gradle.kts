tasks {

    jar {

        archiveBaseName.set("BowSpleef-arena-${rootProject.version}")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )

    }

}

dependencies {
    implementation(project(":common"))

    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
}
