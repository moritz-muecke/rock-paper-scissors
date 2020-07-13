package de.moritz_muecke.rockpaperscissors.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameSessionCompanionTest {

    @Test
    fun `gameSessionFactory should create a GameSession matching to the given properties`() {
        val (playerOneName, playerTwoName) = Pair("Luke", "Leia")
        val roundsString = "100"
        val generatedGameSession = GameSession.gameSessionFactory(roundsString, playerOneName, playerTwoName)

        assertTrue(generatedGameSession.isRight())
        generatedGameSession.map {
            assertEquals(it.players.first.name, playerOneName)
            assertEquals(it.players.second.name, playerTwoName)
            assertEquals(it.rounds, roundsString.toInt())
        }
    }

    @Test
    fun `gameSessionFactory should return Left(ErrorMessage) when rounds argument can't be casted to int`() {
        val generatedGameSession = GameSession.gameSessionFactory("notANumber", "Luke", "Leia")
        assertTrue(generatedGameSession.isLeft())
        generatedGameSession.mapLeft {
            assertEquals(it.message, "Given rounds parameter is not a number!")
        }
    }

    @Test
    fun `gameSessionFactory should return Left(ErrorMessage) when round count exceeds limit`() {
        val generatedGameSession = GameSession.gameSessionFactory("50001", "Luke", "Leia")
        assertTrue(generatedGameSession.isLeft())
        generatedGameSession.mapLeft {
            assertEquals(it.message, "Game does not support more than 50000 rounds!")
        }
    }

    @Test
    fun `gameSessionFactory should return Left(ErrorMessage) when player names are equal`() {
        val generatedGameSession = GameSession.gameSessionFactory("12", "Luke", "Luke")
        assertTrue(generatedGameSession.isLeft())
        generatedGameSession.mapLeft {
            assertEquals(it.message, "Both players have the same name. Be more creative!")
        }
    }

    @Test
    fun `gameSessionFactory should return Left(ErrorMessage) when player names are to long`() {
        val generatedGameSession1 = GameSession.gameSessionFactory("10", "a".repeat(21), "Luke")
        val generatedGameSession2 = GameSession.gameSessionFactory("12", "Luke", "a".repeat(21))
        val generatedGameSession3 = GameSession.gameSessionFactory("12", "a".repeat(21), "b".repeat(21))

        listOf(generatedGameSession1, generatedGameSession2, generatedGameSession3).forEach { session ->
            assertTrue(session.isLeft())
            session.mapLeft {
                assertEquals(it.message, "Please stick to max 20 characters per name. You are ruining my CLI Design!")
            }
        }
    }

}