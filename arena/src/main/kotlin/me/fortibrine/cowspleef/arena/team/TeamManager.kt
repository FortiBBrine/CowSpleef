package me.fortibrine.cowspleef.arena.team

import me.fortibrine.cowspleef.arena.config.teams.TeamsConfig
import me.fortibrine.cowspleef.arena.config.teams.section.Team
import me.fortibrine.cowspleef.arena.event.GameStartEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton
import java.util.*

@Singleton
class TeamManager (
    private val plugin: Plugin,
    private val teamsConfig: TeamsConfig
): Listener {

    val items = mutableMapOf<ItemStack, Team>()
    val teams = teamsConfig.config?.teams ?: listOf()

    @EventHandler(priority = EventPriority.LOW)
    fun shufflePlayers(event: GameStartEvent) {

        val playersInTeams = mutableSetOf<UUID>()
        this.teams.forEach { team ->
            playersInTeams.addAll(team.inPlayers)
        }

        val onlinePlayers = plugin.server.onlinePlayers
            .map { it.uniqueId }

        val playersNotInTeam = (onlinePlayers - playersInTeams).toMutableList()

        playersNotInTeam.shuffle()

        while (this.hasFree()) {

            if (playersNotInTeam.isEmpty()) break

            this.teams.forEach { team ->
                if (!team.isFree()) return@forEach
                if (playersNotInTeam.isEmpty()) return@forEach

                this.join(team, playersNotInTeam.first())
                playersNotInTeam.removeFirst()

            }
        }

    }

    fun join(team: Team, uniqueId: UUID) {
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

    fun join(team: Team, player: Player) {
        join(team, player.uniqueId)
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

    fun hasFree(): Boolean {
        teams.forEach {
            if (it.isFree()) {
                return true
            }
        }
        return false
    }

    fun removeTeam(uniqueId: UUID) {
        teams.forEach { team ->
            if (team.inPlayers.contains(uniqueId)) {
                team.inPlayers.remove(uniqueId)
            }
        }
    }

    fun removeTeam(player: Player) {
        removeTeam(player.uniqueId)
    }

}