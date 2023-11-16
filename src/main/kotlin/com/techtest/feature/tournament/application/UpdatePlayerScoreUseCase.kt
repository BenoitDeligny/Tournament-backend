package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import com.techtest.feature.tournament.domain.port.TournamentStorage
import java.util.*

class UpdatePlayerScoreUseCase(private val tournamentStorage: TournamentStorage) {

    fun handle(tournamentId: UUID, identity: Identity, score: Score): Tournament {
        return tournamentStorage.updatePlayerScore(tournamentId, identity, score)
    }
}