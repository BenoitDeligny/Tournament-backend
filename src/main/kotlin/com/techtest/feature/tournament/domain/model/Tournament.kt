package com.techtest.feature.tournament.domain.model

import java.util.*

class Tournament() {
    var tournamentId: UUID = UUID.randomUUID()
        private set

    var players: MutableSet<Player> = mutableSetOf()
        private set

    var tournamentStatus = TournamentStatus.ONGOING
        private set

    constructor(tournamentId: UUID, players: MutableSet<Player>, tournamentStatus: TournamentStatus) : this() {
        this.tournamentId = tournamentId
        this.players = players
        this.tournamentStatus = tournamentStatus
    }

    fun addPlayer(player: Player) {
        if (players.contains(player)) {
            throw IllegalArgumentException("Player ${player.identity.alias} is already in the tournament")
        } else {
            players.add(player)
            updateRanking()
        }
    }

    fun updatePlayerScore(identity: Identity, score: Score): Player {
        val playerToUpdate = players.find { it.identity.alias == identity.alias }

        if (playerToUpdate == null) {
            throw IllegalArgumentException("Player ${identity.alias} doesn't not belong to this tournament.")
        } else {
            playerToUpdate.updateScore(score.totalPoints)
            updateRanking()
            return playerToUpdate
        }
    }

    fun finishTournament() {
        players.clear()
        tournamentStatus = TournamentStatus.FINISHED
    }

    fun getPlayerInformation(playerName: String): Player {
        val player = players.find { it.identity.alias == playerName }

        if (player == null) {
            throw IllegalArgumentException("Player $playerName doesn't not belong to this tournament.")
        } else {
            return player
        }
    }

    private fun updateRanking() {
        players = players
            .sortedBy { it.score.totalPoints }
            .reversed()
            .mapIndexed { index, player ->
                player.updateRank(index + 1)
                player
            }
            .toMutableSet()
    }

}

enum class TournamentStatus {
    ONGOING,
    FINISHED
}