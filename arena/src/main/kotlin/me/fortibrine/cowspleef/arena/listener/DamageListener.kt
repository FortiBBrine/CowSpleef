package me.fortibrine.cowspleef.arena.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.koin.core.annotation.Singleton

@Singleton
class DamageListener: Listener {

    @EventHandler
    fun damage(event: EntityDamageByEntityEvent) {
        event.isCancelled = true
    }

}