package me.fortibrine.cowspleef.arena.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class GameStartEvent: Event() {

    override fun getHandlers() = handlerList

    companion object {
        @JvmStatic
        val handlerList = HandlerList()
    }

}