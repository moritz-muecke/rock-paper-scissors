package de.moritz_muecke.rockpaperscissors.engine

import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.SingleMatch
import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull


class GameEngineTest {
    private val engine = object : GameEngine {
        override fun runMatch(players: Pair<Player, Player>): SingleMatchResult {
            return mockk()
        }
    }

    private val players = Pair(Player(name = "Luke"), Player(name = "Leia"))

    @Test
    fun `PlayerA wins when choosing ROCK while PlayerB chooses SCISSORS`() {
        val singleMatch = SingleMatch(players, Action.ROCK, Action.SCISSOR)
        assertEquals(engine.determineWinner(singleMatch), players.first)
    }

    @Test
    fun `PlayerA looses when choosing ROCK while PlayerB chooses PAPER`() {
        val singleMatch = SingleMatch(players, Action.ROCK, Action.PAPER)
        assertEquals(engine.determineWinner(singleMatch), players.second)
    }

    @Test
    fun `PlayerA wins when choosing PAPER while PlayerB chooses ROCK`() {
        val singleMatch = SingleMatch(players, Action.PAPER, Action.ROCK)
        assertEquals(engine.determineWinner(singleMatch), players.first)
    }

    @Test
    fun `PlayerA looses when choosing PAPER while PlayerB chooses SCISSORS`() {
        val singleMatch = SingleMatch(players, Action.PAPER, Action.SCISSOR)
        assertEquals(engine.determineWinner(singleMatch), players.second)
    }

    @Test
    fun `PlayerA wins when choosing SCISSORS while PlayerB chooses PAPER`() {
        val singleMatch = SingleMatch(players, Action.SCISSOR, Action.PAPER)
        assertEquals(engine.determineWinner(singleMatch), players.first)
    }

    @Test
    fun `PlayerA looses when choosing SCISSORS while PlayerB chooses ROCK`() {
        val singleMatch = SingleMatch(players, Action.SCISSOR, Action.ROCK)
        assertEquals(engine.determineWinner(singleMatch), players.second)
    }

    @Test
    fun `There is no winner when both PlayerA and PlayerB chooses the same action`() {
        val singleDrawMatch = SingleMatch(players, Action.ROCK, Action.ROCK)
        assertNull(engine.determineWinner(singleDrawMatch))
        assertNull(engine.determineWinner(singleDrawMatch.copy(playerOneAction = Action.PAPER, playerTwoAction = Action.PAPER)))
        assertNull(engine.determineWinner(singleDrawMatch.copy(playerOneAction = Action.SCISSOR, playerTwoAction = Action.SCISSOR)))
    }


}