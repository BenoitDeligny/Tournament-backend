package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateTournamentUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: CreateTournamentUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = CreateTournamentUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldSaveTournament() {
        // given
        val tournament = Tournament()

        // when
        useCase.handle(tournament)

        // then
        assertTrue(storage.contains(tournament))
    }
}