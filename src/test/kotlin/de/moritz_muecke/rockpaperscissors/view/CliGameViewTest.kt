package de.moritz_muecke.rockpaperscissors.view

import arrow.core.Some
import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CliGameViewTest {
    private val rounds = 10
    private val gameEngineMock = mockk<GameEngine>()
    private val gameSession = GameSession(rounds, Pair(Player(name = "Luke"), Player(name = "Leia")))
    private val cliView = CliGameView(gameEngineMock, gameSession)

    @Test
    fun `DisplayGameView calls the game engine as expected`() {
        val expectedResult = SingleMatchResult(Some(gameSession.players.first), Action.ROCK, Action.SCISSOR)
        every { gameEngineMock.runMatch(gameSession.players) } returns expectedResult
        cliView.displayGameView()
        verify(exactly = rounds) { gameEngineMock.runMatch(gameSession.players) }
    }

}