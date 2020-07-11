package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession

/**
 * This interface should be implemented to create any kind of view to expose the game to an user interface
 */
interface GameView {
    val gameEngine: GameEngine
    val gameSession: GameSession
    fun displayGameView(): Unit
}