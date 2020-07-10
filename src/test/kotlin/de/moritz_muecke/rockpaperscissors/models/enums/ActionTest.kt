package de.moritz_muecke.rockpaperscissors.models.enums

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ActionTest {
    private val rock = Action.ROCK
    private val paper = Action.PAPER
    private val scissor = Action.SCISSOR

    @Test
    fun `ROCK must beat SCISSORS`() {
        assertEquals(rock.beats(), Action.SCISSOR)
    }

    @Test
    fun `PAPER must beat ROCK`() {
        assertEquals(paper.beats(), Action.ROCK)
    }

    @Test
    fun `SCISSOR must beat PAPER`() {
        assertEquals(scissor.beats(), Action.PAPER)
    }
}