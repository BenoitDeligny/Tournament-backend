package com.techtest.feature.tournament.exposition

import kotlinx.serialization.Serializable

@Serializable
data class TournamentResponse(
    val tournamentId: String,
    val players: List<PlayerResponse>,
    val status: String
)