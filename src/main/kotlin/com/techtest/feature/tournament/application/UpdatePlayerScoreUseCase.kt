package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.*

class UpdatePlayerScoreUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID, player: Player) {
        tournamentStorage.updatePlayerScore(tournamentId, player)
    }
}