package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Tournament
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.UUID

class AddPlayerUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID, player: Player): Tournament {
        return tournamentStorage.addPlayer(tournamentId, player)
    }
}