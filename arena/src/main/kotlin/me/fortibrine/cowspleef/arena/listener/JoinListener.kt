package me.fortibrine.cowspleef.arena.listener

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class JoinListener(
    private val plugin: Plugin
): Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val player = event.player

        player.gameMode = GameMode.ADVENTURE
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

}