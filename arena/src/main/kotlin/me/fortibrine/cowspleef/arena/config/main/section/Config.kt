package me.fortibrine.cowspleef.arena.config.main.section

import kotlinx.serialization.Serializable

@Serializable
data class Config (
    val database: Database,
    val game: Game
)
