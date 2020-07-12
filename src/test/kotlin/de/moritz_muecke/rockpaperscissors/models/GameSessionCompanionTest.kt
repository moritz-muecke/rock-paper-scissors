package de.moritz_muecke.rockpaperscissors.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class GameSessionCompanionTest {

    @Test
    fun `gameSessionFactory should create a GameSession matching to the given properties`() {
        val (playerOneName, playerTwoName) = Pair("Luke", "Leia")
        val roundsString = "100"
        val gameSession = GameSession.gameSessionFactory(roundsString, playerOneName, playerTwoName)
        assertEquals(gameSession.players.first.name, playerOneName)
        assertEquals(gameSession.players.second.name, playerTwoName)
        assertEquals(gameSession.rounds, roundsString.toInt())
    }

    @Test
    fun `gameSessionFactory should throw an IllegalArgumentException when rounds argument can't be casted to int`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("notANumber", "Luke", "Leia")
        }
        assertEquals(exception.message,"Given rounds parameter is not a number!")
    }

    @Test
    fun `gameSessionFactory should throw an IllegalArgumentException when round count exceeds limit`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("50001", "Luke", "Leia")
        }
        assertEquals(exception.message,"Game does not support more than 50000 rounds!")
    }

    @Test
    fun `gameSessionFactory should throw an IllegalArgumentException when player names are equal`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("12", "Luke", "Luke")
        }
        assertEquals(exception.message,"Both players have the same name. Be more creative!")
    }

    @Test
    fun `gameSessionFactory should throw an IllegalArgumentException when player names are to long`() {
        val ex1 = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("10", "a".repeat(21), "Luke")
        }

        val ex2 = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("12", "Luke", "a".repeat(21))
        }

        val ex3 = assertThrows(IllegalArgumentException::class.java) {
            GameSession.gameSessionFactory("12", "a".repeat(21), "b".repeat(21))
        }

        listOf(ex1, ex2, ex3).forEach {
            assertEquals(it.message,"Please stick to max 20 characters per name. You are ruining my CLI Design!")
        }
    }

}