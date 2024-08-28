package me.fortibrine.cowspleef.common.data.mariadb

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Players : Table("players") {
    val username: Column<String> = varchar("username", 32)
    val wins: Column<Int> = integer("wins")
    val losses: Column<Int> = integer("losses")

    override val primaryKey = PrimaryKey(username)
}
