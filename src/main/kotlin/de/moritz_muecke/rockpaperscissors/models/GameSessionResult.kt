package de.moritz_muecke.rockpaperscissors.models

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.extensions.list.functorFilter.filterMap
import arrow.core.toOption

data class GameSessionResult(val matchResults: List<SingleMatchResult>, val gameSession: GameSession) {

    private val playersSummary = calculatePlayersSummary()

    val firstPlayerSummary = playersSummary.first

    val secondPlayerSummary = playersSummary.second

    val draws: Int = matchResults.filter { it.winner == None }.size

    fun winnersSummary(): Option<PlayerSummary> {
        if(playersSummary.first.wins == playersSummary.second.wins) return None
        return playersSummary.toList().maxBy { it.wins }.toOption()
    }

    private fun calculatePlayersSummary(): Pair<PlayerSummary, PlayerSummary> {
        val matchesWithWinner = matchResults.filterMap { it.winner }
        val playerOneSummary = PlayerSummary(
            gameSession.players.first.name,
            matchesWithWinner.filter { it == gameSession.players.first }.size)
        val playerTwoSummary = PlayerSummary(
            gameSession.players.second.name,
            matchesWithWinner.filter { it == gameSession.players.second }.size)
        return Pair(playerOneSummary, playerTwoSummary)
    }
}
