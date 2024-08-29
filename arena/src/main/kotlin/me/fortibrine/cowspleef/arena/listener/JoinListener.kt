package me.fortibrine.cowspleef.arena.listener

import io.github.bananapuncher714.nbteditor.NBTEditor
import me.fortibrine.cowspleef.arena.service.CooldownService
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class JoinListener(
    private val plugin: Plugin,
    private val cooldownService: CooldownService
): Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val player = event.player
        val chooseCommandItem = ItemStack(Material.BOOK).apply {
            val itemMeta = this.itemMeta!!
            itemMeta.setDisplayName("\u00a77Выбрать команду")
            this.itemMeta = itemMeta
        }

        player.teleport(Bukkit.getWorlds()[0].spawnLocation)

        player.gameMode = GameMode.ADVENTURE
        player.inventory.clear()
        player.inventory.setItem(4, NBTEditor.set(chooseCommandItem, true, "chooseCommand"))
    }

    @EventHandler
    fun login(event: AsyncPlayerPreLoginEvent) {
        if (cooldownService.isStarted) {
            event.disallow(
                AsyncPlayerPreLoginEvent.Result.KICK_FULL,
                "\u00a7cИгра уже начата"
            )
            return
        }
    }

}