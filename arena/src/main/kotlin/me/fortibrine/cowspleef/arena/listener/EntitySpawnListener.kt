package me.fortibrine.cowspleef.arena.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.koin.core.annotation.Singleton

@Singleton
class EntitySpawnListener: Listener {

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        event.isCancelled = true
    }

}