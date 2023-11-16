package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Tournament
import com.techtest.feature.tournament.domain.port.TournamentStorage

class CreateTournamentUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(): Tournament {
        return tournamentStorage.save(Tournament())
    }
}