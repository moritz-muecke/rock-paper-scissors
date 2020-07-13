package de.moritz_muecke.rockpaperscissors.models

import arrow.core.None
import arrow.core.Some
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


class GameSessionResultTest {
    private val firstPlayer = Player(name = "Luke")
    private val secondPlayer = Player(name = "Leia")

    @Test
    fun `PlayerSummary names and wins count should be correct`() {
        val result = createGameSessionResult(5, 3, 2)
        assertEquals(result.firstPlayerSummary.name, firstPlayer.name)
        assertEquals(result.firstPlayerSummary.wins, 5)
        assertEquals(result.secondPlayerSummary.name, secondPlayer.name)
        assertEquals(result.secondPlayerSummary.wins, 3)
    }

    @Test
    fun `No winners summary should be returned when both players have the same win count`() {
        val result = createGameSessionResult(5, 5, 5)
        assertEquals(None, result.winnersSummary())
    }

    @Test
    fun `Draw count should be 6`() {
        val result = createGameSessionResult(4, 5, 6)
        assertEquals(result.draws, 6)
    }

    @Test
    fun `First player should be the winner`() {
        val result = createGameSessionResult(5, 0, 10)
        assertEquals(result.winnersSummary(), Some(PlayerSummary(firstPlayer.name, 5)))
    }

    @Test
    fun `Second player should be the winner`() {
        val result = createGameSessionResult(5, 10, 2)
        assertEquals(result.winnersSummary(), Some(PlayerSummary(secondPlayer.name, 10)))
    }

    private fun createGameSessionResult(firstPlayerWinCount: Int, secondPlayerWinCount: Int, drawCount: Int): GameSessionResult {
        val gamesSum: Int = firstPlayerWinCount + secondPlayerWinCount + drawCount
        val gameSession = GameSession(gamesSum, Pair(firstPlayer, secondPlayer))
        val firstPlayerWins = (1..firstPlayerWinCount).map { _ -> SingleMatchResult(Some(firstPlayer), Action.ROCK, Action.SCISSOR) }
        val secondPlayerWins = (1..secondPlayerWinCount).map { _ -> SingleMatchResult(Some(secondPlayer), Action.SCISSOR, Action.PAPER) }
        val draws = (1..drawCount).map {_ -> SingleMatchResult(None, Action.ROCK, Action.ROCK)}
        return GameSessionResult(firstPlayerWins + secondPlayerWins + draws, gameSession)
    }
}