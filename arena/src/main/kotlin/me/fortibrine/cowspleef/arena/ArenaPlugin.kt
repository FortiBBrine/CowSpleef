package me.fortibrine.cowspleef.arena

import me.fortibrine.cowspleef.api.data.PlayerManager
import me.fortibrine.cowspleef.arena.config.main.MainConfig
import me.fortibrine.cowspleef.arena.modules.KoinConfig
import org.bukkit.Difficulty
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ksp.generated.module

class ArenaPlugin: JavaPlugin() {

    override fun onEnable() {
        startKoin {
            modules(KoinConfig().module, module {
                single { this@ArenaPlugin } bind Plugin::class
            })
        }

        val mainConfig: MainConfig = getKoin().get()

        if (mainConfig.config == null) {
            this.logger.warning("Database config wrong")
            this.server.pluginManager.disablePlugin(this)
            return
        }

        val database = Database.connect(
            driver = "org.mariadb.jdbc.Driver",
            url = "jdbc:mysql://${mainConfig.config!!.database.host}:3306/${mainConfig.config!!.database.database}?useSSL=false",
            user = mainConfig.config!!.database.username,
            password = mainConfig.config!!.database.password,
        )

        val playerManager: PlayerManager = getKoin().get()

        val listeners: List<Listener> = getKoin().getAll()
        listeners.forEach { listener ->
            this.server.pluginManager.registerEvents(listener, this@ArenaPlugin)
        }

        this.server.worlds.forEach { world ->
            world.isAutoSave = false
            world.difficulty = Difficulty.PEACEFUL
        }

    }

    override fun onDisable() {
        stopKoin()
    }

}