package me.fortibrine.cowspleef.arena.team

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryHolder
import org.koin.core.annotation.Singleton

@Singleton
class ChooseTeamMenu(
    private val teamManager: TeamManager,
): InventoryHolder {

    private val inventory = Bukkit.createInventory(
        this,
        teamManager.teams.size - teamManager.teams.size % 9 + 9,
        "Выбрать команду"
    )

    init {
        buildMenu()
    }

    private fun buildMenu() {
        teamManager.items.clear()
        inventory.clear()
        teamManager.teams.forEach { team ->
            inventory.addItem(
                teamManager.toItem(team)
            )
        }
    }

    override fun getInventory() = inventory

    fun click(event: InventoryClickEvent) {
        if (event.currentItem == null) return
        val player = event.whoClicked as Player
        val item = event.currentItem!!

        val team = teamManager.items[item]

        if (team != null) {
            teamManager.join(team, player)
            buildMenu()
        }
    }

}
