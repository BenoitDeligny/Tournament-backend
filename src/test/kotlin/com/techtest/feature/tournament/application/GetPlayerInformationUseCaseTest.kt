package com.techtest.feature.tournament.application

import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetPlayerInformationUseCaseTest {
    private lateinit var storage: FakeTournamentStorage
    private lateinit var useCase: GetPlayerInformationUseCase

    @BeforeEach
    fun setUp() {
        storage = FakeTournamentStorage()
        useCase = GetPlayerInformationUseCase(storage)

        storage.clear()
    }

    @Test
    fun handle_shouldReturnPlayerInformation() {
        // given
        val tournament = Tournament()

        val playerOne = Player(Identity("PlayerOne"), Score())
        playerOne.updateScore(4)

        val playerTwo = Player(Identity("PlayerTwo"), Score())
        playerTwo.updateScore(5)

        val bestialPlayer = Player(Identity("bestialPlayer"), Score())
        bestialPlayer.updateScore(666)

        tournament.addPlayer(playerOne)
        tournament.addPlayer(playerTwo)
        tournament.addPlayer(bestialPlayer)

        storage.save(tournament)

        tournament.updatePlayerScore(Identity("PlayerOne"), Score(5))

        storage.save(tournament)

        // when
        val winner = useCase.handle(tournament.tournamentId, "bestialPlayer")
        val middler = useCase.handle(tournament.tournamentId, "PlayerOne")
        val loser = useCase.handle(tournament.tournamentId, "PlayerTwo")

        // then
        assertEquals("bestialPlayer", winner.identity.alias)
        assertEquals(666, winner.score.totalPoints)
        assertEquals(1, winner.ranking)

        assertEquals("PlayerOne", middler.identity.alias)
        assertEquals(9, middler.score.totalPoints)
        assertEquals(2, middler.ranking)

        assertEquals("PlayerTwo", loser.identity.alias)
        assertEquals(5, loser.score.totalPoints)
        assertEquals(3, loser.ranking)
    }


    @Test
    fun handle_shouldReturnException_whenPlayerNotBelongToTournament() {
        // given
        val tournament = Tournament()

        val playerOne = Player(Identity("PlayerOne"), Score())
        playerOne.updateScore(5)

        val playerTwo = Player(Identity("PlayerTwo"), Score())
        playerTwo.updateScore(4)

        val bestialPlayer = Player(Identity("bestialPlayer"), Score())
        bestialPlayer.updateScore(666)

        tournament.addPlayer(playerOne)
        tournament.addPlayer(playerTwo)
        tournament.addPlayer(bestialPlayer)

        storage.save(tournament)

        // when
        val exception = assertThrows(RuntimeException::class.java) {
            useCase.handle(tournament.tournamentId, "NOOB")
        }

        // then
        assertEquals("Player NOOB doesn't not belong to this tournament.", exception.message)
    }
}