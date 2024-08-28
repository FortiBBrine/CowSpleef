package me.fortibrine.cowspleef.common.data.mariadb

import me.fortibrine.cowspleef.api.Player
import me.fortibrine.cowspleef.api.data.PlayerManager
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class MariadbPlayerManager: PlayerManager {

    init {
        transaction {
            if (!Players.exists()) {
                SchemaUtils.create(Players)
            }
        }
    }

    override suspend fun getPlayer(username: String): Player =
        transaction {
            val resultRow = Players.selectAll()
                .where { Players.username eq username }
                .singleOrNull()

            if (resultRow == null) {
                return@transaction Player(username)
            }

            return@transaction Player(
                username = username,
                wins = resultRow[Players.wins],
                losses = resultRow[Players.losses]
            )
        }

    override suspend fun getAllPlayers(): List<Player> =
        transaction {

            val players = mutableListOf<Player>()

            Players.selectAll().forEach { resultRow ->
                players.add(Player(
                    username = resultRow[Players.username],
                    wins = resultRow[Players.wins],
                    losses = resultRow[Players.losses]
                ))
            }

            return@transaction players
        }

    override suspend fun setPlayer(username: String, player: Player): Unit =
        transaction {

            if (Players.selectAll()
                .where { Players.username eq username }
                .singleOrNull() != null) {

                Players.update({ Players.username eq username }) {
                    it[wins] = player.wins
                    it[losses] = player.losses
                }
            } else {
                Players.insert {
                    it[this.username] = username
                    it[wins] = player.wins
                    it[losses] = player.losses
                }
            }

        }

}