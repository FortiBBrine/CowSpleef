package me.fortibrine.cowspleef.arena.team

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.koin.core.annotation.Factory

@Factory
class ChooseTeamMenu(
    val teamManager: TeamManager,
): InventoryHolder {

    private val inventory = Bukkit.createInventory(
        this,
        27,
        "Выбрать команду"
    )

    init {

    }

    override fun getInventory(): Inventory {

    }

}