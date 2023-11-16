package com.techtest.feature.tournament.exposition

import kotlinx.serialization.Serializable

@Serializable
data class ScoreRequest(val score: Int)