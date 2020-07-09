package de.moritz_muecke.rockpaperscissors.models.enums

enum class Action {
    ROCK, PAPER, SCISSOR;

    fun beats() = when(this) {
        ROCK -> SCISSOR
        PAPER -> ROCK
        SCISSOR -> PAPER
    }
}