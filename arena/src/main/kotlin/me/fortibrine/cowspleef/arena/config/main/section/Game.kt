package me.fortibrine.cowspleef.arena.config.main.section

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game (
    @SerialName("need-players")
    val needPlayers: Int,
    @SerialName("max-players")
    val maxPlayers: Int
)
