package me.fortibrine.cowspleef.arena.config.teams.section

import kotlinx.serialization.Serializable

@Serializable
data class Config (
    val teams: List<Team>,
)
