package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FinishTournamentUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: FinishTournamentUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = FinishTournamentUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldRemoveAllPlayersAndFinishTournament() {
        // given
        val tournament = Tournament()
        val playerOne = Player(Identity("PlayerOne"), Score())
        val playerTwo = Player(Identity("PlayerTwo"), Score())

        tournament.addPlayer(playerOne)
        tournament.addPlayer(playerTwo)

        storage.save(tournament);

        // when
        useCase.handle(tournament.tournamentId)

        // then
        assertTrue(tournament.tournamentStatus == TournamentStatus.FINISHED)
        assertTrue(tournament.players.size == 0)
    }
}