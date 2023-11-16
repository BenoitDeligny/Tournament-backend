package com.techtest.feature.tournament.domain.model

class Score {
    var totalPoints: Int = 0
        private set;

    fun increase(points: Int) {
        totalPoints += points
    }
}
