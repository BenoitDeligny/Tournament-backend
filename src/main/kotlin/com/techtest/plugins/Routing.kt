package com.techtest.plugins

import com.techtest.feature.tournament.application.*
import com.techtest.feature.tournament.exposition.tournament
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello guys! Be gentle with this, i'm not familiar with Kotlin and MongoDb. And a complete newbie with Ktor and Koin.")
        }

        val createTournament by inject<CreateTournamentUseCase>()
        val fetchTournament by inject<FetchTournamentUseCase>()
        val finishTournament by inject<FinishTournamentUseCase>()
        val addPlayerUseCase by inject<AddPlayerUseCase>()
        val getPlayerInformationUseCase by inject<GetPlayerInformationUseCase>()
        val updatePlayerScoreUseCase by inject<UpdatePlayerScoreUseCase>()

        tournament(
            createTournament,
            fetchTournament,
            finishTournament,
            addPlayerUseCase,
            getPlayerInformationUseCase,
            updatePlayerScoreUseCase
        )
    }
}