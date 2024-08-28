package me.fortibrine.cowspleef.arena.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent
import org.koin.core.annotation.Singleton

@Singleton
class DropListener: Listener {

    @EventHandler
    fun onDrop(event: PlayerDropItemEvent) {
        event.isCancelled = true
    }

}