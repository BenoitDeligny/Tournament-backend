package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.UUID

class GetPlayerInformationUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID, playerAlias: String): Player {
        return tournamentStorage.getPlayerInformation(tournamentId, playerAlias)
    }
}