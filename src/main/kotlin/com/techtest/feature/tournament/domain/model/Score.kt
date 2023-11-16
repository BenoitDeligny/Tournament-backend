package com.techtest.feature.tournament.domain.model

class Score() {
    var totalPoints: Int = 0
        private set

    constructor(totalPoints: Int) : this() {
        this.totalPoints = totalPoints
    }

    fun update(points: Int) {
        totalPoints += points
    }

}
