package com.techtest.feature.tournament.infra

import com.mongodb.client.MongoCollection
import com.techtest.feature.tournament.domain.model.*
import com.techtest.feature.tournament.domain.model.TournamentStatus.FINISHED
import com.techtest.feature.tournament.domain.port.TournamentStorage
import io.ktor.server.plugins.*
import org.litote.kmongo.KMongo
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.replaceOne
import java.util.*

class TournamentRepository : TournamentStorage {

    // TODO: not sure how to refactor this (where should i put it and how)
    private val clientConnection = "mongodb://mongodb:27017"
    private val client = KMongo.createClient(clientConnection)
    private val database = client.getDatabase("mydb")
    private val tournamentCollection = database.getCollection<TournamentDocument>()

    override fun save(tournament: Tournament): Tournament {
        tournamentCollection.insertOne(toTournamentDocument(tournament))
        return tournament
    }

    override fun find(tournamentId: UUID): Tournament {
        return toTournamentDomain(tournamentCollection.findTournamentByIdOrThrow(tournamentId.toString()))
    }

    override fun addPlayer(tournamentId: UUID, player: Player): Tournament {
        return toTournamentDomain(tournamentCollection.findTournamentByIdOrThrow(tournamentId.toString()))
            .apply { addPlayer(player) }
            .also { tournamentCollection.replaceOne<TournamentDocument>(toTournamentDocument(it)) }
    }

    override fun updatePlayerScore(tournamentId: UUID, identity: Identity, score: Score): Tournament {
        return toTournamentDomain(tournamentCollection.findTournamentByIdOrThrow(tournamentId.toString()))
            .apply { updatePlayerScore(identity, score) }
            .also { tournamentCollection.replaceOne<TournamentDocument>(toTournamentDocument(it)) }
    }

    override fun finishTournament(tournamentId: UUID): Tournament {
        return toTournamentDomain(tournamentCollection.findTournamentByIdOrThrow(tournamentId.toString()))
            .apply { finishTournament() }
            .also { tournamentCollection.replaceOne<TournamentDocument>(toTournamentDocument(it)) }
    }

    override fun getPlayerInformation(tournamentId: UUID, playerName: String): Player {
        return toTournamentDomain(tournamentCollection.findTournamentByIdOrThrow(tournamentId.toString()))
            .getPlayerInformation(playerName)
    }


    private fun MongoCollection<TournamentDocument>.findTournamentByIdOrThrow(tournamentId: String): TournamentDocument {
        val tournament = findOneById(tournamentId)

        if (tournament == null) {
            throw NotFoundException("Tournament $tournamentId was not found.")
        } else if (isTournamentFinished(tournament.status)) {
            throw IllegalArgumentException("Tournament $tournamentId is finished. You can't access it anymore.")
        } else {
            return tournament
        }
    }

    private fun isTournamentFinished(status: String): Boolean {
        return status == FINISHED.name
    }

    private fun toTournamentDocument(tournament: Tournament): TournamentDocument {
        return TournamentDocument(
            tournament.tournamentId.toString(),
            tournament.players.map { toPlayerData(it) },
            tournament.tournamentStatus.name
        )
    }

    private fun toPlayerData(player: Player): PlayerData {
        return PlayerData(
            player.identity.alias,
            player.score.totalPoints,
            player.ranking
        )
    }

    private fun toTournamentDomain(tournamentDocument: TournamentDocument): Tournament {
        return Tournament(
            UUID.fromString(tournamentDocument.tournamentId),
            tournamentDocument.players.map { toPlayerDomain(it) }.toMutableSet(),
            TournamentStatus.valueOf(tournamentDocument.status)
        )
    }

    private fun toPlayerDomain(playerData: PlayerData): Player {
        return Player(
            Identity(playerData.alias),
            Score(playerData.score),
            playerData.rank
        )
    }
}
