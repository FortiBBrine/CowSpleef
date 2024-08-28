package me.fortibrine.cowspleef.arena.service

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton
import kotlin.math.min

@Singleton(createdAtStart = true)
class CooldownService(
    private val plugin: Plugin
): Listener {

    private val needPlayers = 2
    private val maxPlayers = 2

    private val startUpTime = System.currentTimeMillis()
    var cooldown =
        startUpTime + 3 * 60 * 1000L

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val online = plugin.server.onlinePlayers.size

        if (online == needPlayers) {
            cooldown = min(cooldown, System.currentTimeMillis() + 15 * 1000L)
        }
    }

}
