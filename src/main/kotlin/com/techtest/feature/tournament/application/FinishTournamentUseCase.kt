package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.*

class FinishTournamentUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID) {
        tournamentStorage.finishTournament(tournamentId)
    }
}