package me.fortibrine.cowspleef.arena.team

import me.fortibrine.cowspleef.arena.config.teams.section.Team
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.koin.core.annotation.Singleton

@Singleton
class TeamManager {

    private val teams = mutableListOf<Team>()

    fun join(team: Team, player: Player) {
        val uniqueId = player.uniqueId

        if (team.inPlayers.size >= team.players) {
            return
        }

        team.inPlayers.add(uniqueId)
    }

    fun toItem(team: Team): ItemStack {
        return ItemStack(team.material).apply {
            val itemMeta = itemMeta
            itemMeta?.setDisplayName("" + team.color + team.name)

            itemMeta?.lore =
                team.inPlayers
                .map { Bukkit.getOfflinePlayer(it).name }
                .map { "" +ChatColor.WHITE + it }

            this.itemMeta = itemMeta
        }
    }

}