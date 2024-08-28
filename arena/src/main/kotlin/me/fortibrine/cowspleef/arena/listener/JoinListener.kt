package me.fortibrine.cowspleef.arena.listener

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.cowspleef.api.data.PlayerManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class JoinListener(
    private val plugin: Plugin,
    private val playerManager: PlayerManager
): Listener {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        scope.launch {
            val player = playerManager.getPlayer(event.player.name)

            plugin.logger.info(player.toString())

            player.wins += 1
            playerManager.setPlayer(event.player.name, player)
        }
    }

}