package com.techtest.feature.tournament.domain.port

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Tournament
import java.util.*

interface TournamentStorage {
    fun save(tournament: Tournament)
    fun find(tournamentId: UUID): Tournament
    fun addPlayer(tournamentId: UUID, player: Player)
    fun updatePlayerScore(tournamentId: UUID, player: Player)
    fun finishTournament(tournamentId: UUID)
    fun getPlayerInformation(tournamentId: UUID, playerName: String): Player
}