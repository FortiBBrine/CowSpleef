tasks {

    jar {

        archiveBaseName.set("BowSpleef-lobby-${rootProject.version}")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )

    }

}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.spigot)
}