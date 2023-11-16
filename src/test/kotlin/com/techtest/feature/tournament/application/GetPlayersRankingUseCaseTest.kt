package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetPlayersRankingUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: GetPlayersRankingUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = GetPlayersRankingUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldReturnPlayerRankingForTournament() {
        // given
        val tournament = Tournament()

        val playerOne = Player(Identity("PlayerOne"), Score())
        playerOne.increaseScore(5)

        val playerTwo = Player(Identity("PlayerTwo"), Score())
        playerTwo.increaseScore(4)

        val bestialPlayer = Player(Identity("bestialPlayer"), Score())
        bestialPlayer.increaseScore(666)

        tournament.addPlayer(playerOne)
        tournament.addPlayer(playerTwo)
        tournament.addPlayer(bestialPlayer)

        storage.save(tournament)

        // when
        val rankedPlayers = useCase.handle(tournament.tournamentId)

        // then
        assertEquals(3, rankedPlayers.size)
        assertTrue(rankedPlayers.indexOf(bestialPlayer) == 0)
        assertTrue(rankedPlayers.indexOf(playerOne) == 1)
        assertTrue(rankedPlayers.indexOf(playerTwo) == 2)
    }
}