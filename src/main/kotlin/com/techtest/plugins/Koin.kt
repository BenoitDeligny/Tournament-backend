package com.techtest.plugins

import com.techtest.feature.tournament.application.*
import com.techtest.feature.tournament.domain.port.TournamentStorage
import com.techtest.feature.tournament.infra.TournamentRepository
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger
import org.koin.dsl.module

fun Application.configureKoin() {
    startKoin {
        slf4jLogger()
        modules(tournamentModule)
    }
}

val tournamentModule = module {
    single<TournamentStorage> { TournamentRepository() }
    single { CreateTournamentUseCase(get()) }
    single { FetchTournamentUseCase(get()) }
    single { FinishTournamentUseCase(get()) }
    single { AddPlayerUseCase(get()) }
    single { GetPlayerInformationUseCase(get()) }
    single { UpdatePlayerScoreUseCase(get()) }
}