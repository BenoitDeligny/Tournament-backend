package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Tournament
import com.techtest.feature.tournament.domain.port.TournamentStorage

import java.util.*
import kotlin.collections.ArrayList

class FakeTournamentStorage : ArrayList<Tournament>(), TournamentStorage {
    override fun save(tournament: Tournament) {
        this.add(tournament);
    }

    override fun find(tournamentId: UUID): Tournament {
        val tournament = this.find { it.tournamentId == tournamentId }

        if (tournament == null) {
            throw RuntimeException("Tournament $tournamentId was not found.")
        } else {
            return tournament
        }
    }

    override fun addPlayer(tournamentId: UUID, player: Player) {
        val tournament = find(tournamentId)
        tournament.addPlayer(player)
    }

    override fun updatePlayerScore(tournamentId: UUID, player: Player) {
        val tournament = find(tournamentId)
        tournament.updatePlayerScore(player)
    }

    override fun finishTournament(tournamentId: UUID) {
        val tournament = find(tournamentId)
        tournament.finishTournament()
    }

    override fun getPlayerInformation(tournamentId: UUID, playerName: String): Player {
        val tournament = find(tournamentId)
        return tournament.getPlayerInformation(playerName)
    }

}
