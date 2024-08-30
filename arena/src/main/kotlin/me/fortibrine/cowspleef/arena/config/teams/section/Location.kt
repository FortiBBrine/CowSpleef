package me.fortibrine.cowspleef.arena.config.teams.section

import kotlinx.serialization.Serializable
import org.bukkit.Bukkit

@Serializable
data class Location (
    val world: String,
    val x: Double,
    val y: Double,
    val z: Double,
) {
    fun toBukkitLocation(): org.bukkit.Location {
        return org.bukkit.Location (
            Bukkit.getWorld(world)!!,
            x,
            y,
            z,
        )
    }
}

fun org.bukkit.Location.toConfigLocation(): Location {
    return Location(
        world = this.world?.name ?: "world",
        x = this.x,
        y = this.y,
        z = this.z
    )
}
