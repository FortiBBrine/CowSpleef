package me.fortibrine.cowspleef.arena.team

import me.fortibrine.cowspleef.arena.config.teams.TeamsConfig
import me.fortibrine.cowspleef.arena.config.teams.section.Team
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.koin.core.annotation.Singleton

@Singleton
class TeamManager (
    private val teamsConfig: TeamsConfig
) {

    val items = mutableMapOf<ItemStack, Team>()
    val teams = teamsConfig.config?.teams ?: listOf()

    fun join(team: Team, player: Player) {
        val uniqueId = player.uniqueId

        if (team.inPlayers.size >= team.players) {
            return
        }

        teams.forEach {
            if (it.inPlayers.contains(uniqueId)) {
                it.inPlayers.remove(uniqueId)
            }
        }

        team.inPlayers.add(uniqueId)
    }

    fun getTeam(player: Player): Team? {
        teams.forEach {
            if (it.inPlayers.contains(player.uniqueId)) {
                return it
            }
        }
        return null
    }

    fun toItem(team: Team): ItemStack {
        val item = ItemStack(team.material).apply {
            val itemMeta = itemMeta
            itemMeta?.setDisplayName("" + team.color + team.name)

            itemMeta?.lore =
                team.inPlayers
                    .map { Bukkit.getOfflinePlayer(it).name }
                    .map { "" +ChatColor.WHITE + it }

            this.itemMeta = itemMeta
        }

        items[item] = team

        return item
    }

}