package de.moritz_muecke.rockpaperscissors.engine

import de.moritz_muecke.rockpaperscissors.models.SingleMatchResult
import de.moritz_muecke.rockpaperscissors.models.Player
import de.moritz_muecke.rockpaperscissors.models.SingleMatch
import de.moritz_muecke.rockpaperscissors.models.enums.Action
import de.moritz_muecke.rockpaperscissors.models.enums.Result

interface GameEngine {
    fun runMatch(players: Pair<Player, Player>): SingleMatchResult

    /**
     * This logic should always apply, no matter which kind of interactions and logic is implemented
     */
    fun determineWinner(singleMatch: SingleMatch): Player? {
        return when (rockPaperScissorLogic(singleMatch.playerOneAction, singleMatch.playerTwoAction)) {
            Result.WIN -> singleMatch.players.first
            Result.LOSE -> singleMatch.players.second
            Result.DRAW -> null
        }
    }

    /**
     * This logic should always apply, no matter which kind of interactions and logic is implemented
     */
    private fun rockPaperScissorLogic(playerAction: Action, opponentAction: Action): Result {
        return when (playerAction) {
            opponentAction.beats() -> Result.LOSE
            opponentAction -> Result.DRAW
            else -> Result.WIN
        }
    }

}