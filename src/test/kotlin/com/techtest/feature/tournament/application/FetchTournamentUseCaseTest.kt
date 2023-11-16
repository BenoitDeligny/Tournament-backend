package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FetchTournamentUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: FetchTournamentUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = FetchTournamentUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldFindTournament() {
        // given
        val tournament = Tournament()

        storage.add(tournament)

        // when
        val expectedTournament = useCase.handle(tournament.tournamentId)

        // then
        assertTrue(expectedTournament?.tournamentId == tournament.tournamentId)
    }

    @Test
    fun handle_shouldNotFindTournament() {
        // given
        val firstTournament = Tournament()
        val secondTournament = Tournament()

        storage.add(firstTournament)

        // when
        val exception = assertThrows(RuntimeException::class.java) {
            useCase.handle(secondTournament.tournamentId)
        }

        // then
        assertEquals("Tournament ${secondTournament.tournamentId} was not found.", exception.message)
    }
}