package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddPlayerUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: AddPlayerUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = AddPlayerUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldAddPlayerInTournament() {
        // given
        val tournament = Tournament()
        val playerOne = Player(Identity("PlayerOne"), Score())

        storage.save(tournament);

        // when
        useCase.handle(tournament.tournamentId, playerOne)

        // then
        assertTrue(tournament.players.contains(playerOne))
        assertTrue(tournament.players.size == 1)
    }


    @Test
    fun handle_shouldThrow_whenPlayerAlreadyExistInTournament() {
        // given
        val tournament = Tournament()
        val playerOne = Player(Identity("PlayerOne"), Score())

        tournament.addPlayer(playerOne)

        storage.save(tournament);

        // when
        val exception = assertThrows(IllegalArgumentException::class.java) {
            useCase.handle(tournament.tournamentId, playerOne)
        }

        // then
        assertEquals("Player PlayerOne is already in the tournament", exception.message)
        assertTrue(tournament.players.contains(playerOne))
        assertTrue(tournament.players.size == 1)
    }
}