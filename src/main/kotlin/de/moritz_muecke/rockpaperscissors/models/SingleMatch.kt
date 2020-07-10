package de.moritz_muecke.rockpaperscissors.models

import de.moritz_muecke.rockpaperscissors.models.enums.Action

data class SingleMatch(val players: Pair<Player, Player>, val playerOneAction: Action, val playerTwoAction: Action)