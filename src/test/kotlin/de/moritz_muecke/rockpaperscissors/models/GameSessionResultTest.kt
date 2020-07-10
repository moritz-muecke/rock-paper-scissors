package de.moritz_muecke.rockpaperscissors.models

import de.moritz_muecke.rockpaperscissors.models.enums.Action
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


class GameSessionResultTest {
    private val playerA = Player(name = "Luke")
    private val playerB = Player(name = "Leia")

    private val playerAMatches = 4
    private val playerBMatches = 5
    private val drawMatches = 6
    private val playerAWins = (1..playerAMatches).map { _ -> SingleMatchResult(playerA, Action.ROCK, Action.SCISSOR) }
    private val playerBWins = (1..playerBMatches).map { _ -> SingleMatchResult(playerB, Action.SCISSOR, Action.PAPER) }
    private val draws = (1..drawMatches).map {_ -> SingleMatchResult(null, Action.ROCK, Action.ROCK)}

    private val gameSessionResult = GameSessionResult(playerAWins + playerBWins + draws)

    @Test
    fun `Win count for playerA should be 4`() {
        gameSessionResult.playerWinCount(playerA)
        assertEquals(gameSessionResult.playerWinCount(playerA), 4)
    }

    @Test
    fun `Win count for playerB should be 5`() {
        gameSessionResult.playerWinCount(playerA)
        assertEquals(gameSessionResult.playerWinCount(playerB), 5)
    }

    @Test
    fun `Draw count should be 6`() {
        gameSessionResult.playerWinCount(playerA)
        assertEquals(gameSessionResult.drawCount(), 6)
    }
}