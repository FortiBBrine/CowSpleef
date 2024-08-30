package me.fortibrine.cowspleef.arena.service

import kotlinx.coroutines.*
import me.fortibrine.cowspleef.arena.config.main.MainConfig
import me.fortibrine.cowspleef.arena.event.GameStartEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton

@Singleton
class CooldownService(
    private val plugin: Plugin,
    private val mainConfig: MainConfig
): Listener {

    private val needPlayers = mainConfig.config?.game?.needPlayers ?: 3
    private val maxPlayers = mainConfig.config?.game?.maxPlayers ?: 2

    var startUpTime = System.currentTimeMillis()

    var cooldown: Long? = null
    var isStarted = false

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        plugin.logger.info("Need players for start: $needPlayers")
        plugin.logger.info("Max players for start: $maxPlayers")

        scope.launch {
            repeat(Integer.MAX_VALUE) {
                delay(1000L)

                if (cooldown != null && System.currentTimeMillis() >= cooldown!!) {
                    isStarted = true
                    cooldown = null
                    plugin.server.pluginManager.callEvent(GameStartEvent())
                    HandlerList.unregisterAll(this@CooldownService)
                    cancel()
                }
            }
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val online = plugin.server.onlinePlayers.size

        if (online == maxPlayers) {
            startUpTime = System.currentTimeMillis()
            cooldown = startUpTime + 15 * 1000L
        } else if (online == needPlayers) {
            startUpTime = System.currentTimeMillis()
            cooldown = startUpTime + 60 * 1000L
        } else if (online < needPlayers) {
            cooldown = null
        }
    }

    @EventHandler
    fun quit(event: PlayerQuitEvent) {
        val online = plugin.server.onlinePlayers.size - 1

        if (online < needPlayers) {
            cooldown = null
        }
    }

}
