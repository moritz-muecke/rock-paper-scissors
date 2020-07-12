package de.moritz_muecke.rockpaperscissors.models

data class GameSession(val rounds: Int, val players: Pair<Player, Player>) {

    val playerOneName = players.first.name
    val playerTwoName = players.second.name

    companion object {
        /*
        Setting this to not exceed the default configured GC size.
        The Current implementation runs one game after another without stopping. To allow a much bigger amount of rounds
        Kotlin Flows from kotlinx-coroutines-core package could be used.
        As long as you don't print the detailed logs on the stdout you can of course raise this to a way higher amount
         */
        private const val maxRoundCount = 50000

        private const val nameMaxCharacters = 20

        fun gameSessionFactory(roundsString: String, firstPlayerName: String, secondPlayerName: String): GameSession {

            require(roundsString.toIntOrNull() is Int) { "Given rounds parameter is not a number!" }
            val rounds = roundsString.toInt()

            require(rounds <= maxRoundCount)
                { "Game does not support more than $maxRoundCount rounds!" }
            require(firstPlayerName != secondPlayerName)
                { "Both players have the same name. Be more creative!" }
            require(firstPlayerName.length <= nameMaxCharacters && secondPlayerName.length <= nameMaxCharacters)
                { "Please stick to max $nameMaxCharacters characters per name. You are ruining my CLI Design!" }

            val firstPlayer = Player(name = firstPlayerName)
            val secondsPlayer = Player(name = secondPlayerName)

            return GameSession(
                rounds = rounds,
                players = Pair(firstPlayer, secondsPlayer)
            )
        }
    }
}