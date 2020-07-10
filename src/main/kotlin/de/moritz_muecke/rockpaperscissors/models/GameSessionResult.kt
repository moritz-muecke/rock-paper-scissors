package de.moritz_muecke.rockpaperscissors.models

data class GameSessionResult(val matchResults: List<SingleMatchResult>) {
    fun playerWinCount(player: Player) = matchResults.filter { it.winner == player }.size
    fun drawCount() = matchResults.filter { it.winner == null }.size
}