package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession

interface View {
    val gameEngine: GameEngine
    val gameSession: GameSession
    fun displayGameView(): Unit
}