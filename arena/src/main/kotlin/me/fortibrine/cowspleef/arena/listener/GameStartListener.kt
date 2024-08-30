package me.fortibrine.cowspleef.arena.listener

import me.fortibrine.cowspleef.arena.event.GameStartEvent
import me.fortibrine.cowspleef.arena.team.TeamManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class GameStartListener(
    private val plugin: Plugin,
    private val teamManager: TeamManager
): Listener {

    @EventHandler(priority = EventPriority.HIGH)
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

            val location = teamManager.getTeam(player)?.location

            if (location != null) {
                player.teleport(location.toBukkitLocation())
            }
        }

        plugin.server.broadcastMessage("GAME STARTED")

        val scoreboardManager = plugin.server.scoreboardManager
        if (scoreboardManager != null) {
            val scoreboard = scoreboardManager.mainScoreboard

            teamManager.teams.forEach {
                val team = scoreboard.registerNewTeam(it.name)
                team.setAllowFriendlyFire(false)

                it.inPlayers.forEach { uuid ->
                    team.addPlayer(Bukkit.getOfflinePlayer(uuid))
                }
            }
        }

    }

}