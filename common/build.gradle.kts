dependencies {
    api(project(":api"))

    api(libs.exposed.core)
    api(libs.exposed.dao)
    api(libs.exposed.jdbc)
    api(libs.exposed.kotlin.datetime)
    api(libs.coroutines)
    api(libs.kaml)
    api(libs.mariadb)
}
