package com.techtest.feature.tournament.domain.model

data class Player(
    val identity: Identity,
    val score: Score
) {
    var ranking: Int? = null
        private set

    constructor(identity: Identity, score: Score, ranking: Int) : this(identity, score) {
        this.ranking = ranking
    }

    fun increaseScore(points: Int) {
        score.increase(points);
    }
}
