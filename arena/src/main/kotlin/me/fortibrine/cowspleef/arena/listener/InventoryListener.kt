package me.fortibrine.cowspleef.arena.listener

import me.fortibrine.cowspleef.arena.team.ChooseTeamMenu
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class InventoryListener(
    private val plugin: Plugin
) : Listener {

    @EventHandler
    fun click(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.clickedInventory == player.inventory) {
            event.isCancelled = true
        }

        if (event.clickedInventory == null) return
        if (event.clickedInventory!!.holder == null) return
        val holder = event.clickedInventory!!.holder

        event.isCancelled = true

        if (holder is ChooseTeamMenu) {
            holder.click(event)
        }
    }

    @EventHandler
    fun drag(event: InventoryDragEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun pickUp(event: InventoryPickupItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun moveItem(event: InventoryMoveItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun moveItem(event: PlayerSwapHandItemsEvent) {
        event.isCancelled = true
    }

}
