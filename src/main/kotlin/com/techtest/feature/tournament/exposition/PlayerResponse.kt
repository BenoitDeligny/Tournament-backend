package com.techtest.feature.tournament.exposition

import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    val alias: String,
    val score: Int,
    val rank: Int?
)
