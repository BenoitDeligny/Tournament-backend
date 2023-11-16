package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Tournament
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.UUID

class FetchTournamentUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID): Tournament {
        return tournamentStorage.find(tournamentId)
    }
}