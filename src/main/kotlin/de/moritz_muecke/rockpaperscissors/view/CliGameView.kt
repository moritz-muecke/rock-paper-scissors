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

        fun printGameSessionHeader() {
            val header = """
                |-------------------------------------------------------------------------------------------------------------
                | ROCK - PAPER - SCISSORS
                |
                | Welcome our two opponents:
                | ${gameSession.playerOneName} and ${gameSession.playerTwoName}
                |-------------------------------------------------------------------------------------------------------------
                | Get ready for ${gameSession.rounds} incredible Rock-Paper-Scissor matches!
            """.trimIndent()

            println(header)
        }

        fun printGameSessionResult(gameSessionResult: GameSessionResult) {
            val draws = gameSessionResult.drawCount()
            val playerOneWins = gameSessionResult.playerWinCount(gameSession.players.first)
            val playerTwoWins = gameSessionResult.playerWinCount(gameSession.players.second)
            val winnerString =
                if(playerOneWins == playerTwoWins) {
                    "UNBELIEVABLE, WE HAVE NO WINNER! IT'S A DRAW"
                } else "AND THE WINNER IS: ${if(playerOneWins > playerTwoWins) gameSession.playerOneName.toUpperCase() else gameSession.playerTwoName.toUpperCase()}"
            val summary = """
                |-------------------------------------------------------------------------------------------------------------
                | ${gameSessionResult.matchResults.size} Matches were fought bravely!
                |
                | Scoreboard:
                | ${gameSession.playerOneName}: $playerOneWins - ${gameSession.playerTwoName} : $playerTwoWins - Draws: $draws
                |
                | $winnerString
                |-------------------------------------------------------------------------------------------------------------
            """.trimIndent()

            println(summary)
        }

        fun printMatchDetails(matchResult: SingleMatchResult, matchNumber: Int) {
            val winOrDraw = if(matchResult.winner != null) {
                "${matchResult.winner.name} has won this match"
            } else "OMG, we have a draw! Can this be more exiting?"

            val matchSummary = """
                |-------------------------------------------------------------------------------------------------------------
                | 
                | Finished game number $matchNumber
                | ${gameSession.playerOneName} has chosen ${matchResult.playerOneAction} - ${gameSession.playerTwoName} chooses ${matchResult.playerTwoAction}
                | $winOrDraw
                |
            """.trimIndent()

            println(matchSummary)
        }
    }
}