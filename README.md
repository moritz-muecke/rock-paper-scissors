# Rock Paper Scissors

Simple Rock-Paper-Scissors game code kata written in [Kotlin](https://kotlinlang.org/). It just simulates computer
controlled players. The implementation matches the following requirements:
- FirstPlayer pick randomly `ROCK`, `PAPER` or `SCISSORS`, the other should always choose `ROCK`
- The Game should play 100 rounds per default
- The Game should evaluate the game logic, i.e. the result (e.g. `WIN`, `DRAW`, `LOSE`) of two actions 
(e.g. `ROCK`, `PAPER`, `SCISSOR`) competing against each other
- The application should calculate at least the number of wins for each player as well as the number of draws.


## Custom parameters

Per default the players are named Luke and Leia and they are playing 100 rounds against each other without detailed logging.
You are able to customize those parameters by passing those four arguments to the application:
- arg1: Number of rounds (Limited to 10000)
- arg2: Name of the first player (Limited to 20 characters to keep the stdout clean)
- arg3: Name of the second player (Limited to 20 characters to keep the stdout clean)
- arg4: Boolean flag (`true`/`false`) for detailed game logging. Every string which does not match `true` will be parsed
 as `false`
    - Be careful with this argument. Since a lot of additional logs are written to stdout it costs a lot of performance
     especially at game session > 5000 matches


### Execution with gradle

It is not necessary to have gradle installed on your machine. If you have cloned the repository you can use 
[The Gradle-Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) to execute the game.


#### With default parameters

- `./gradlew run`


#### With custom parameters

- `./gradlew run --args="10 Cloud Squall true"`

## Testing

Unit Tests are written in JUnit5. To execute them you can also use [The Gradle-Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
- `./gradlew test`
