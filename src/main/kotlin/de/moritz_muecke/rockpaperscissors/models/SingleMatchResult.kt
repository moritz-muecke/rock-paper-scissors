package de.moritz_muecke.rockpaperscissors.models

import arrow.core.Option
import de.moritz_muecke.rockpaperscissors.models.enums.Action

data class SingleMatchResult(val winner: Option<Player>, val playerOneAction: Action, val playerTwoAction: Action)