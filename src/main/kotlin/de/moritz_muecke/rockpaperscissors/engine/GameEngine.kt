package de.moritz_muecke.rockpaperscissors.engine

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
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
    fun determineWinner(singleMatch: SingleMatch): Option<Player> {
        return when (rockPaperScissorLogic(singleMatch.playerOneAction, singleMatch.playerTwoAction)) {
            Result.WIN -> Some(singleMatch.players.first)
            Result.LOSE -> Some(singleMatch.players.second)
            Result.DRAW -> None
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