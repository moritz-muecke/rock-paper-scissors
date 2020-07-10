package de.moritz_muecke.rockpaperscissors.models

data class GameSession(val rounds: Int, val players: Pair<Player, Player>) {

    val playerOneName = players.first.name
    val playerTwoName = players.second.name

    companion object {
        private const val maxRoundCount = 100000

        fun initializeGameSession(roundsString: String, firstPlayerName: String, secondPlayerName: String): GameSession {

            require(roundsString.toIntOrNull() is Int) { "Given rounds parameter is not a number!" }
            val rounds = roundsString.toInt()
            require(rounds <= maxRoundCount) { "Game does not support more than $maxRoundCount rounds!" }
            require(firstPlayerName != secondPlayerName) { "Both players have the same name. Be more creative!" }

            val firstPlayer = Player(name = firstPlayerName)
            val secondsPlayer = Player(name = secondPlayerName)

            return GameSession(
                rounds = rounds,
                players = Pair(firstPlayer, secondsPlayer)
            )
        }
    }
}