package me.fortibrine.cowspleef.arena

import me.fortibrine.cowspleef.arena.modules.MainModule
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ksp.generated.module

class ArenaPlugin: JavaPlugin() {

    override fun onEnable() {
        startKoin {
            modules(MainModule().module, module {
                single { this@ArenaPlugin } bind Plugin::class
            })
        }

        val listeners: List<Listener> = getKoin().getAll()
        listeners.forEach { listener ->
            this.server.pluginManager.registerEvents(listener, this@ArenaPlugin)
        }

    }

    override fun onDisable() {
        stopKoin()
    }

}