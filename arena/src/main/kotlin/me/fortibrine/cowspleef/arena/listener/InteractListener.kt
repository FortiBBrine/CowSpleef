package me.fortibrine.cowspleef.arena.listener

import io.github.bananapuncher714.nbteditor.NBTEditor
import me.fortibrine.cowspleef.arena.team.ChooseTeamMenu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.koin.core.annotation.Singleton
import org.koin.java.KoinJavaComponent.getKoin

@Singleton
class InteractListener: Listener {

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (NBTEditor.contains(item, "chooseCommand")) {
            player.sendMessage("choose")

            val chooseTeamMenu: ChooseTeamMenu = getKoin().get()
            player.openInventory(chooseTeamMenu.inventory)
        }

    }

}