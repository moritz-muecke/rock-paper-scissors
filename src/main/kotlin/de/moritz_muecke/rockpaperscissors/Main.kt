package de.moritz_muecke.rockpaperscissors

import de.moritz_muecke.rockpaperscissors.engine.SimpleAutomatedGameEngine
import de.moritz_muecke.rockpaperscissors.models.GameSession
import de.moritz_muecke.rockpaperscissors.view.CliView
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger("MainFunction")

    val rounds = args.elementAtOrElse(0) { "100" }
    val fistPlayerName = args.elementAtOrElse(1) { "Luke" }
    val secondPlayerName = args.elementAtOrElse(2) { "Leia" }


    val gameSession = try {
        GameSession.gameSessionFactory(
            roundsString = rounds,
            firstPlayerName = fistPlayerName,
            secondPlayerName = secondPlayerName
        )
    } catch (e: Exception) {
        logger.error("Could not initialize game session. Reason: ${e.message} Ending the game now.")
        exitProcess(0)
    }

    val view = CliView(SimpleAutomatedGameEngine())
    view.displayGameView(gameSession)
}
