package com.techtest.feature.tournament.domain.model

class Player(
    val identity: Identity,
    val score: Score
) {
    // TODO: Today, ranking rely on Set index. It means two players with same score don't have the same rank. It's a shame and need to be rework
    // Also, having optional ranking here is maybe not the best
    var ranking: Int? = null
        private set

    constructor(identity: Identity, score: Score, ranking: Int?) : this(identity, score) {
        this.ranking = ranking
    }

    fun updateScore(points: Int) {
        score.update(points)
    }

    fun updateRank(rank: Int) {
        ranking = rank
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return identity == other.identity
    }

    override fun hashCode(): Int {
        return identity.hashCode()
    }

    override fun toString(): String {
        return "Player(identity=$identity, score=$score, ranking=$ranking)"
    }

}
