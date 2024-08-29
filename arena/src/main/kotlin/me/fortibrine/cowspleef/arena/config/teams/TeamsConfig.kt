package me.fortibrine.cowspleef.arena.config.teams

import com.charleskorn.kaml.Yaml
import me.fortibrine.cowspleef.arena.config.teams.section.Config
import org.bukkit.plugin.Plugin
import org.koin.core.annotation.Singleton
import java.io.File
import java.io.FileInputStream

@Singleton(createdAtStart = true)
class TeamsConfig(
    private val plugin: Plugin,
) {

    var config: Config? = null
        private set

    init {
        val configFile = File(plugin.dataFolder, "teams.yml")

        if (!configFile.exists()) {
            plugin.saveResource("teams.yml", false)
        }

        val fileInputStream = FileInputStream(configFile)
        val configString = String(fileInputStream.readBytes())

        config = Yaml.default.decodeFromString(Config.serializer(), configString)

        fileInputStream.close()

//        val fileOutputStream = FileOutputStream(configFile)
//
//        Yaml.default.encodeToStream(Config.serializer(), Config(
//            teams = listOf(
//                Team(
//                    color = ChatColor.RED,
//                    material = Material.RED_STAINED_GLASS_PANE,
//                    name = "Красная",
//                    players = 1
//                ),
//                Team(
//                    color = ChatColor.WHITE,
//                    material = Material.WHITE_STAINED_GLASS_PANE,
//                    name = "Белая",
//                    players = 1
//                ),
//            )
//        ), fileOutputStream)
//
//        fileOutputStream.close()
    }

}