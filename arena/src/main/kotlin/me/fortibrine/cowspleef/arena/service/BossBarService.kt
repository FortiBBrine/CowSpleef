package me.fortibrine.cowspleef.arena.service

import kotlinx.coroutines.*
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton
import java.text.SimpleDateFormat

@Singleton
class BossBarService(
    private val plugin: Plugin,
    private val cooldownService: CooldownService
): Listener {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val bossBar = plugin.server.createBossBar("", BarColor.RED, BarStyle.SEGMENTED_20)

    init {

        val format = SimpleDateFormat("Время до запуска игры: mm:ss")

        scope.launch {
            repeat(Integer.MAX_VALUE) {
                delay(1000L)
                bossBar.progress = System.currentTimeMillis().toDouble() / cooldownService.cooldown
                bossBar.setTitle(
                    format.format(cooldownService.cooldown - System.currentTimeMillis())
                )
            }
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        bossBar.addPlayer(event.player)
    }

}