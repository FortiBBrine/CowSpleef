package me.fortibrine.cowspleef.arena.listener

import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.koin.core.annotation.Singleton

@Singleton
class InventoryListener: Listener {

    fun drag(event: InventoryDragEvent) {
        event.isCancelled = true
    }

    fun pickup(event: InventoryPickupItemEvent) {
        event.isCancelled = true
    }

}
