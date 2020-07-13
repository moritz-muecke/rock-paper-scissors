package de.moritz_muecke.rockpaperscissors.engine

import arrow.core.None
import arrow.core.Some
import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue


class SimpleAutomatedGameEngineTest {

    private val gameEngine = SimpleAutomatedGameEngine()
    private val players = Pair(Player(name = "Luke"), Player(name = "Leia"))

    @Test
    fun `PlayerOneAction should be rock in case of no winner or one player hast to be returned as winner`() {
        val matchResult = gameEngine.runMatch(players)
        assertEquals(matchResult.playerTwoAction, Action.ROCK)

        when(val w = matchResult.winner) {
            is None -> assertEquals(matchResult.playerOneAction, Action.ROCK)
            is Some -> assertTrue(players.toList().contains(w.t))
        }
    }
}