package com.techtest.feature.tournament.domain.port

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import java.util.*

interface TournamentStorage {
    fun save(tournament: Tournament): Tournament
    fun find(tournamentId: UUID): Tournament
    fun addPlayer(tournamentId: UUID, player: Player): Tournament
    fun updatePlayerScore(tournamentId: UUID, identity: Identity, score: Score): Tournament
    fun finishTournament(tournamentId: UUID): Tournament
    fun getPlayerInformation(tournamentId: UUID, playerName: String): Player
}