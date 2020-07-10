package de.moritz_muecke.rockpaperscissors.models

import de.moritz_muecke.rockpaperscissors.models.enums.Action

data class SingleMatchResult(val winner: Player?, val playerOneAction: Action, val playerTwoAction: Action)