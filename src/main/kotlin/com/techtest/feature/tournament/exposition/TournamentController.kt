package com.techtest.feature.tournament.exposition

import com.techtest.feature.tournament.application.*
import com.techtest.feature.tournament.domain.model.Identity
import com.techtest.feature.tournament.domain.model.Player
import com.techtest.feature.tournament.domain.model.Score
import com.techtest.feature.tournament.domain.model.Tournament
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

// TODO: this is the simplest way i found to make it work
fun Route.tournament(
    createTournamentUseCase: CreateTournamentUseCase,
    fetchTournamentUseCase: FetchTournamentUseCase,
    finishTournamentUseCase: FinishTournamentUseCase,
    addPlayerUseCase: AddPlayerUseCase,
    getPlayerInformationUseCase: GetPlayerInformationUseCase,
    updatePlayerScoreUseCase: UpdatePlayerScoreUseCase
) {
    post("/tournament") {
        call.respond(
            HttpStatusCode.OK,
            toTournamentResponse(
                createTournamentUseCase.handle()
            )
        )
    }

    get("/tournament/{id}") {
        call.respond(
            HttpStatusCode.OK,
            toTournamentResponse(fetchTournamentUseCase.handle(UUID.fromString(call.parameters["id"])))
        )
    }

    post("/tournament/{id}/finish") {
        call.respond(
            HttpStatusCode.OK,
            toTournamentResponse(finishTournamentUseCase.handle(UUID.fromString(call.parameters["id"])))
        )
    }

    post("/tournament/{id}/player") {
        call.respond(
            HttpStatusCode.OK,
            toTournamentResponse(
                addPlayerUseCase.handle(
                    UUID.fromString(call.parameters["id"]),
                    toPlayerDomain(call.receive<PlayerRequest>())
                )
            )
        )
    }

    get("/tournament/{id}/player/{name}") {
        call.respond(
            HttpStatusCode.OK,
            toPlayerResponse(
                getPlayerInformationUseCase.handle(
                    UUID.fromString(call.parameters["id"]),
                    call.parameters["name"].toString()
                )
            )
        )
    }

    put("/tournament/{id}/player/{name}") {
        call.respond(
            HttpStatusCode.OK,
            toTournamentResponse(
                updatePlayerScoreUseCase.handle(
                    UUID.fromString(call.parameters["id"]),
                    toIdentityDomain(call.parameters["name"].toString()),
                    toScoreDomain(call.receive<ScoreRequest>())
                )
            )
        )
    }
}

private fun toTournamentResponse(tournament: Tournament): TournamentResponse {
    return TournamentResponse(
        tournament.tournamentId.toString(),
        tournament.players.map { toPlayerResponse(it) },
        tournament.tournamentStatus.name
    )
}

private fun toPlayerResponse(player: Player): PlayerResponse {
    return PlayerResponse(
        player.identity.alias,
        player.score.totalPoints,
        player.ranking
    )
}

private fun toPlayerDomain(player: PlayerRequest): Player {
    return Player(
        Identity(player.alias),
        Score()
    )
}

private fun toIdentityDomain(alias: String): Identity {
    return Identity(alias)
}

private fun toScoreDomain(score: ScoreRequest): Score {
    return Score(score.score)
}