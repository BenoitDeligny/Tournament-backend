package com.techtest.feature.tournament.domain.model

import java.util.MissingResourceException
import java.util.UUID

class Tournament {
    val tournamentId: UUID = UUID.randomUUID()

    var players: MutableSet<Player> = mutableSetOf()
        private set

    var tournamentStatus = TournamentStatus.ONGOING
        private set

    fun addPlayer(player: Player) {
        if (players.contains(player)) {
            throw IllegalArgumentException("Player ${player.identity.alias} is already in the tournament")
        } else {
            players.add(player)
        }
    }

    fun updatePlayerScore(player: Player) {
        players.add(player)
    }

    fun finishTournament() {
        players.clear()
        tournamentStatus = TournamentStatus.FINISHED
    }

    fun ranking(): ArrayList<Player> {
        return ArrayList(players.sortedBy { it.score.totalPoints }.reversed())
    }

    fun getPlayerInformation(playerName: String): Player {
        val rankedPlayers = ranking()

        val player = rankedPlayers.find { it.identity.alias == playerName }
        val playerRanking = rankedPlayers.indexOf(player) + 1

        if (player == null) {
            throw RuntimeException("Player $playerName doesn't not belong to this tournament.")
        } else {
            return Player(player.identity, player.score, playerRanking)
        }
    }

}

enum class TournamentStatus {
    ONGOING,
    FINISHED
}