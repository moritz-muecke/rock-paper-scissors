package de.moritz_muecke.rockpaperscissors

import arrow.core.Either
import de.moritz_muecke.rockpaperscissors.engine.SimpleAutomatedGameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.view.CliGameView
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger("MainFunction")

    val rounds = args.elementAtOrElse(0) { "100" }
    val fistPlayerName = args.elementAtOrElse(1) { "Luke" }
    val secondPlayerName = args.elementAtOrElse(2) { "Leia" }

    val detailedGameLogging = args.elementAtOrElse(3) { "false" }.toBoolean()

    val generatedGameSession = GameSession.gameSessionFactory(
        roundsString = rounds,
        firstPlayerName = fistPlayerName,
        secondPlayerName = secondPlayerName
    )

    when(generatedGameSession) {
        is Either.Left -> {
            logger.error("Could not initialize game session. Reason: ${generatedGameSession.a.message} Ending the game now.")
            exitProcess(0)
        }
        is Either.Right -> {
            val view = CliGameView(
                gameEngine = SimpleAutomatedGameEngine(),
                gameSession = generatedGameSession.b,
                detailedGameLogging = detailedGameLogging
            )
            view.displayGameView()
        }
    }
}

