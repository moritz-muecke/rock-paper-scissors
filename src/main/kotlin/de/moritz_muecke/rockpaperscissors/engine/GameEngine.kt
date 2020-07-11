package de.moritz_muecke.rockpaperscissors.engine

import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult
import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.SingleMatch
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import de.moritz_muecke.rockpaperscissors.models.enums.Result

/**
 * This interface should be implemented to represent the basic game mechanics
 */
interface GameEngine {
    fun runMatch(players: Pair<Player, Player>): SingleMatchResult

    /**
     * This logic should always apply, as it represents the basic rock paper scissor rules
     */
    fun determineWinner(singleMatch: SingleMatch): Player? {
        return when (rockPaperScissorLogic(singleMatch.playerOneAction, singleMatch.playerTwoAction)) {
            Result.WIN -> singleMatch.players.first
            Result.LOSE -> singleMatch.players.second
            Result.DRAW -> null
        }
    }

    private fun rockPaperScissorLogic(playerAction: Action, opponentAction: Action): Result {
        return when (playerAction) {
            opponentAction.beats() -> Result.LOSE
            opponentAction -> Result.DRAW
            else -> Result.WIN
        }
    }

}