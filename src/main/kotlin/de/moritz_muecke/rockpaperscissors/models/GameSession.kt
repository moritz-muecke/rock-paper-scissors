package de.moritz_muecke.rockpaperscissors.models

import arrow.core.Either
import arrow.core.Right
import arrow.core.Left
import de.moritz_muecke.rockpaperscissors.errorhandling.ErrorMessage

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

        fun gameSessionFactory(roundsString: String, firstPlayerName: String, secondPlayerName: String): Either<ErrorMessage, GameSession> {
            return when (val validation = validateInput(roundsString, firstPlayerName, secondPlayerName)) {
                is Either.Left -> validation
                is Either.Right -> {
                    val firstPlayer = Player(name = firstPlayerName)
                    val secondsPlayer = Player(name = secondPlayerName)
                    val rounds = validation.b

                    Right(GameSession(
                            rounds = rounds,
                            players = Pair(firstPlayer, secondsPlayer)
                    ))
                }
            }
        }

        private fun validateInput(roundsString: String, firstPlayerName: String, secondPlayerName: String): Either<ErrorMessage, Int> {
            if(roundsString.toIntOrNull() == null) return Left(ErrorMessage("Given rounds parameter is not a number!"))
            val rounds = roundsString.toInt()

            if(rounds > maxRoundCount) return Left(ErrorMessage("Game does not support more than $maxRoundCount rounds!"))

            if(firstPlayerName == secondPlayerName) return Left(ErrorMessage("Both players have the same name. Be more creative!"))

            if(firstPlayerName.length > nameMaxCharacters || secondPlayerName.length > nameMaxCharacters) {
                return Left(ErrorMessage("Please stick to max $nameMaxCharacters characters per name. You are ruining my CLI Design!"))
            }
            return Right(roundsString.toInt())
        }
    }
}
