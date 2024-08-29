package me.fortibrine.cowspleef.arena.listener

import me.fortibrine.cowspleef.arena.event.GameStartEvent
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class GameStartListener(
    private val plugin: Plugin,
): Listener {

    @EventHandler
    fun gameStart(event: GameStartEvent) {
        plugin.server.onlinePlayers.forEach { player ->
            player.inventory.clear()
            player.inventory.addItem(ItemStack(Material.BOW).apply {
                addEnchantment(Enchantment.ARROW_FIRE, 1)
                addEnchantment(Enchantment.ARROW_INFINITE, 1)

                val itemMeta = this.itemMeta
                itemMeta?.isUnbreakable = true
                itemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE)
                this.itemMeta = itemMeta
            })
            player.inventory.addItem(ItemStack(Material.ARROW))
        }
        plugin.server.broadcastMessage("GAME STARTED")
    }

}