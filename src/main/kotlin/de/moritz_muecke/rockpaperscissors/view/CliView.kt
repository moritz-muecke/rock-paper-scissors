package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.models.GameSessionResult

class CliView(override val gameEngine: GameEngine) : View {

    override fun displayGameView(gameSession: GameSession): Unit {

        printLnHorizontal()
        printWithRowEdge("ROCK - PAPER - SCISSORS")
        printLnHorizontal()
        printWithRowEdge("Welcome our two opponents:")
        printWithRowEdge("${gameSession.playerOneName} and ${gameSession.playerTwoName}")
        printLnHorizontal()
        printWithRowEdge("Get ready for ${gameSession.rounds} incredible Rock-Paper-Scissor matches!")

        val gameSessionResult = runGameSession(gameSession)

        val draws = gameSessionResult.drawCount()
        val playerOneWins = gameSessionResult.playerWinCount(gameSession.players.first)
        val playerTwoWins = gameSessionResult.playerWinCount(gameSession.players.second)
        printLnHorizontal()
        printWithRowEdge("${gameSessionResult.matchResults.size} Matches were fought bravely!")
        printEmptyLn()
        printWithRowEdge("Scoreboard:")
        printWithRowEdge("${gameSession.playerOneName}: $playerOneWins - ${gameSession.playerTwoName}: $playerTwoWins - Draws: $draws")
        printEmptyLn()
        if(playerOneWins == playerTwoWins) {
            printWithRowEdge("UNBELIEVABLE, WE HAVE NO WINNER! IT'S A DRAW")
        } else printWithRowEdge("AND THE WINNER IS: ${if(playerOneWins > playerTwoWins) gameSession.playerOneName.toUpperCase() else gameSession.playerTwoName.toUpperCase()}")
        printLnHorizontal()

    }

    private fun runGameSession(gameSession: GameSession): GameSessionResult {
        val matchResults = (1..gameSession.rounds).map { i ->
            printLnHorizontal()
            printEmptyLn()
            printWithRowEdge("Running game number $i")
            val matchResult = gameEngine.runMatch(gameSession.players)
            printWithRowEdge("${gameSession.playerOneName} has chosen ${matchResult.playerOneAction} - ${gameSession.playerTwoName} chooses ${matchResult.playerTwoAction}")
            if(matchResult.winner != null) {
                printWithRowEdge("The winner is ${matchResult.winner.name}")
            } else printWithRowEdge("OMG, we have a draw! Can this be more exiting?")
            printEmptyLn()
            matchResult
        }

        return GameSessionResult(matchResults)
    }

    private val lineWith = 110
    private val rowEdge = "|"
    private val horizontal = rowEdge + "-".repeat(lineWith - 1)
    private val emptyLine = rowEdge + " ".repeat(lineWith - 1)

    private fun printWithRowEdge(text: String) {
        println("$rowEdge$text")
    }

    private fun printLnHorizontal() {
        println(horizontal)
    }

    private fun printEmptyLn() {
        println(emptyLine)
    }
}