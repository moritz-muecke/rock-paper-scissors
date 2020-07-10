package de.moritz_muecke.rockpaperscissors.engine

import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.SingleMatch
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult

/**
 * This is a very simple implementation of GameEngine. It just plays by itself and only chooses a random action for the
 * first player. The second player always chooses rock.
 */
class SimpleAutomatedGameEngine : GameEngine {
    private val playerTwoAction = Action.ROCK

    override fun runMatch(players: Pair<Player, Player>): SingleMatchResult {
        val playerOneAction = Action.randomAction()
        val match = SingleMatch(players, playerOneAction, playerTwoAction)
        val winner = determineWinner(match)

        return SingleMatchResult(winner, playerOneAction, playerTwoAction)
    }
}