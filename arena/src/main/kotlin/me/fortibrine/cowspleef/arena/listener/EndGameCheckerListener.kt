package me.fortibrine.cowspleef.arena.listener

import me.fortibrine.cowspleef.arena.team.TeamManager
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class EndGameCheckerListener(
    private val teamManager: TeamManager,
    private val plugin: Plugin,
): Listener {

    private fun check() {
        val countPlayers = teamManager.teams
            .filter { it.inPlayers.size != 0 }

        if (countPlayers.isEmpty()) {
            plugin.server.shutdown()
        }

        if (countPlayers.size == 1) {
            val lastTeam = countPlayers.first()

            lastTeam.inPlayers.forEach {
                val offlinePlayer = Bukkit.getOfflinePlayer(it)
                offlinePlayer.player?.sendTitle(
                    "Вы победили",
                    "Поздравляем и ждём вас ещё!"
                )
            }

            plugin.server.shutdown()
        }
    }

    @EventHandler
    fun quit(event: PlayerQuitEvent) {
        teamManager.removeTeam(event.player.uniqueId)

        check()
    }

    @EventHandler
    fun death(event: PlayerDeathEvent) {
        val player = event.entity

        player.spigot().respawn()
        player.gameMode = GameMode.SPECTATOR

        teamManager.removeTeam(player)

        check()
    }

}
