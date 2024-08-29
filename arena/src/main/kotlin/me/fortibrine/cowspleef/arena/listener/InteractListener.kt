package me.fortibrine.cowspleef.arena.listener

import io.github.bananapuncher714.nbteditor.NBTEditor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.koin.core.annotation.Singleton

@Singleton
class InteractListener: Listener {

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (NBTEditor.contains(item, "chooseCommand")) {
            player.sendMessage("choose")
        }

    }

}