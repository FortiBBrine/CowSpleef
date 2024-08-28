package me.fortibrine.cowspleef.arena.config.main.section

import kotlinx.serialization.Serializable

@Serializable
data class Database (
    val host: String,
    val database: String,
    val username: String,
    val password: String
)
