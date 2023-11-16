package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpdatePlayerScoreUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: UpdatePlayerScoreUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = UpdatePlayerScoreUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldUpdatePlayerScore() {
        // given
        val tournament = Tournament()
        val playerOne = Player(Identity("PlayerOne"), Score())

        storage.save(tournament);

        playerOne.increaseScore(5)

        // when
        useCase.handle(tournament.tournamentId, playerOne)

        // then
        assertEquals(5, tournament.players.find { it == playerOne }?.score?.totalPoints)
        assertTrue(tournament.players.size == 1)
    }
}

