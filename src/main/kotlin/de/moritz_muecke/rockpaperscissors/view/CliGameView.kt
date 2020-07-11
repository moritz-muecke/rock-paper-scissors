package de.moritz_muecke.rockpaperscissors.view

import de.moritz_muecke.rockpaperscissors.engine.GameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.models.GameSessionResult
import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult

/**
 * A View implementation which uses the CLI / stdout to display a game session
 */
class CliGameView(override val gameEngine: GameEngine, override val gameSession: GameSession, private val detailedGameLogging: Boolean = false) : GameView {

    private val stdOutHelper = StdOutHelper()

    override fun displayGameView() {
        stdOutHelper.printGameSessionHeader()
        val gameSessionResult = runGameSession(gameSession)
        stdOutHelper.printGameSessionResult(gameSessionResult)
    }

    private fun runGameSession(gameSession: GameSession): GameSessionResult {
        val matchResults = (1..gameSession.rounds).map {
            val matchResult = gameEngine.runMatch(gameSession.players)
            if (detailedGameLogging) {
                stdOutHelper.printMatchDetails(matchResult, it)
            }
            matchResult
        }
        return GameSessionResult(matchResults)
    }

    inner class StdOutHelper {
        private val lineWith = 110
        private val rowEdge = "|"
        private val horizontal = rowEdge + "-".repeat(lineWith - 1)
        private val emptyLine = rowEdge + " ".repeat(lineWith - 1)

        fun printGameSessionHeader() {
            printLnHorizontal()
            printWithRowEdge("ROCK - PAPER - SCISSORS")
            printLnHorizontal()
            printWithRowEdge("Welcome our two opponents:")
            printWithRowEdge("${gameSession.playerOneName} and ${gameSession.playerTwoName}")
            printLnHorizontal()
            printWithRowEdge("Get ready for ${gameSession.rounds} incredible Rock-Paper-Scissor matches!")
        }

        fun printGameSessionResult(gameSessionResult: GameSessionResult) {
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

        fun printMatchDetails(matchResult: SingleMatchResult, matchNumber: Int) {
            printLnHorizontal()
            printEmptyLn()
            printWithRowEdge("Finished game number $matchNumber")
            printWithRowEdge("${gameSession.playerOneName} has chosen ${matchResult.playerOneAction} - ${gameSession.playerTwoName} chooses ${matchResult.playerTwoAction}")
            if(matchResult.winner != null) {
                printWithRowEdge("The winner is ${matchResult.winner.name}")
            } else printWithRowEdge("OMG, we have a draw! Can this be more exiting?")
            printEmptyLn()
        }

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
}