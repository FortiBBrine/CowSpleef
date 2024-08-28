package me.fortibrine.cowspleef.api.data

import me.fortibrine.cowspleef.api.Player

interface PlayerManager {

    suspend fun getPlayer(username: String): Player
    suspend fun getAllPlayers(): List<Player>
    suspend fun setPlayer(username: String, player: Player)

}
