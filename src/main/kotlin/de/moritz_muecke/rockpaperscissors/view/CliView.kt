package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.models.GameSessionResult

class CliView(override val gameEngine: GameEngine) : View {
    override fun displayGameView(gameSession: GameSession): Unit {

        println("Welcome our two opponents $gameSession.firstPlayerName and $gameSession.secondPlayerName.")
        println("Get ready for ${gameSession.rounds} incredible Rock-Paper-Scissor matches!")

        val gameSessionResult = runGameSession(gameSession)

        val draws = gameSessionResult.drawCount()
        val playerOneWins = gameSessionResult.playerWinCount(gameSession.players.first)
        val playerTwoWins = gameSessionResult.playerWinCount(gameSession.players.second)
        println("${gameSessionResult.matchResults.size} Matches were fought bravely!")
        println("${gameSession.playerOneName} has won $playerOneWins Matches, ${gameSession.playerTwoName} has won $playerTwoWins and we have $draws Draws!")

    }

    private fun runGameSession(gameSession: GameSession): GameSessionResult {
        val matchResults = (1..gameSession.rounds).map { i ->
            println("Running game number $i")
            val matchResult = gameEngine.runMatch(gameSession.players)
            println("${gameSession.playerOneName} has chosen ${matchResult.playerOneAction} - ${gameSession.playerTwoName} chooses ${matchResult.playerTwoAction}")
            if(matchResult.winner != null) {
                println("The winner is ${matchResult.winner.name}")
            } else println("OMG, we have a draw! Can this be more exiting?")

            matchResult
        }

        return GameSessionResult(matchResults)
    }
}