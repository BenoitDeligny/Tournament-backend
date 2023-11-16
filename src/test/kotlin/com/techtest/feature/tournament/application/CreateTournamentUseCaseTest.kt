package com.techtest.feature.tournament.application

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

        // when
        useCase.handle()

        // then
        assertTrue(storage.size == 1)
    }
}