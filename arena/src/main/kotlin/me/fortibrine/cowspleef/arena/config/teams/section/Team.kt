package me.fortibrine.cowspleef.arena.config.teams.section

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.bukkit.ChatColor
import org.bukkit.Material
import java.util.UUID

@Serializable
data class Team (
    val color: ChatColor,
    val material: Material,
    val name: String,
    val players: Int,
    val location: Location
) {
    @Transient
    val inPlayers: MutableList<UUID> = mutableListOf()
}
