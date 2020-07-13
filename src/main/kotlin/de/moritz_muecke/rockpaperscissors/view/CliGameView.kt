package de.moritz_muecke.rockpaperscissors.view

import arrow.core.None
import arrow.core.Some
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

        return GameSessionResult(matchResults, gameSession)
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
            val winnerString =
                when(val winner = gameSessionResult.winnersSummary()) {
                    is None -> "UNBELIEVABLE, WE HAVE NO WINNER! IT'S A DRAW"
                    is Some -> "AND THE WINNER IS: ${winner.t.name}"
                }

            val firstPlayerWins = gameSessionResult.firstPlayerSummary.wins
            val secondPlayerWins = gameSessionResult.secondPlayerSummary.wins
            val draws = gameSessionResult.draws

            val summary = """
                |-------------------------------------------------------------------------------------------------------------
                | ${gameSessionResult.matchResults.size} Matches were fought bravely!
                |
                | Scoreboard:
                | ${gameSessionResult.firstPlayerSummary.name}: $firstPlayerWins - ${gameSession.playerTwoName} : $secondPlayerWins - Draws: $draws
                |
                | $winnerString
                |-------------------------------------------------------------------------------------------------------------
            """.trimIndent()

            println(summary)
        }

        fun printMatchDetails(matchResult: SingleMatchResult, matchNumber: Int) {
            val winOrDraw = when(val w = matchResult.winner) {
                is None -> "OMG, we have a draw! Can this be more exiting?"
                is Some -> "${w.t.name} has won this match"
            }

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