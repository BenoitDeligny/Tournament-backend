package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.UUID

class GetPlayersRankingUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID): ArrayList<Player> {
        val tournament = tournamentStorage.find(tournamentId)
        return tournament.ranking()
    }
}