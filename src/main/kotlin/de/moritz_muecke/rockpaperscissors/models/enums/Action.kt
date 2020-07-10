package de.moritz_muecke.rockpaperscissors.models.enums

import kotlin.random.Random

enum class Action {
    ROCK, PAPER, SCISSOR;

    fun beats() = when(this) {
        ROCK -> SCISSOR
        PAPER -> ROCK
        SCISSOR -> PAPER
    }

    companion object {
        private val valueList = values().toList()
        fun randomAction() = valueList[Random.nextInt(valueList.size)]
    }
}