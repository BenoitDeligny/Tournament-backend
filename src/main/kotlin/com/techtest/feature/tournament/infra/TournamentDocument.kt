package com.techtest.feature.tournament.infra

import org.bson.codecs.pojo.annotations.BsonId

data class TournamentDocument(
    @BsonId
    val tournamentId: String,
    val players: List<PlayerData>,
    val status: String
)