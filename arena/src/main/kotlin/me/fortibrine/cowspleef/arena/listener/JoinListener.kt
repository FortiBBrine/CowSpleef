package me.fortibrine.cowspleef.arena.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton(createdAtStart = true, binds = [Listener::class])
class JoinListener(
    private val plugin: Plugin
): Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.sendMessage("hello")
    }

}