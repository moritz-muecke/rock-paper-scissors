package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession

interface View {
    val gameEngine: GameEngine
    fun displayGameView(gameSession: GameSession): Unit
}