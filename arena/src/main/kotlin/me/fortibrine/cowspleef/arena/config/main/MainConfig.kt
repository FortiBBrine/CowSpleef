package me.fortibrine.cowspleef.arena.config.main

import com.charleskorn.kaml.Yaml
import me.fortibrine.cowspleef.arena.config.main.section.Config
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton
import java.io.File
import java.io.FileInputStream

@Singleton(createdAtStart = true)
class MainConfig(
    private val plugin: Plugin,
) {

    var config: Config? = null
        private set

    init {
        val configFile = File(plugin.dataFolder, "config.yml")

        if (!configFile.exists()) {
            plugin.config.options().copyDefaults(true)
            plugin.saveDefaultConfig()
        }

        val fileInputStream = FileInputStream(configFile)
        val configString = String(fileInputStream.readBytes())

        config = Yaml.default.decodeFromString(Config.serializer(), configString)

        fileInputStream.close()
    }

}