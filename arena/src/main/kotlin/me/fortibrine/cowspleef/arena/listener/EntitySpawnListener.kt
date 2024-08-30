package me.fortibrine.cowspleef.arena.listener

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.koin.core.annotation.Singleton

@Singleton
class EntitySpawnListener: Listener {

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        if (event.entity.type != EntityType.ARROW) {
            event.isCancelled = true
        }
    }

}