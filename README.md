# Hitman-cli
Hitman game in java based on cli.

## Features implemented
- Player can create new player and start the game.
- Weapons can be changed depending on the money.
- User can view their details.
- There are 3 levels in the game which player needs to cross to win the series.
- With each level, player gets money credited in their account which can be used to buy new weapons.
- As soon as the game starts, system and player start hitting each other.
- System player will hit the player at fixed speed whereas player can hit the system player as fast as possible.
- Anytime game can be paused and progress can be saved and it can be resumed from the same state.
- If player looses in higher level then they will have to start from beginning.
- Input validation is performed at each level.

## Possible enhancements
- Users can be allowed to have multiple weapons at the same time and they should be able to change it while hitting the system player. 
- Users can be allowed to add custom weapons.

## To generate the jar
- mvn clean package (You must have maven installed)

## If you don't have maven installed, Don't worry. Use below commands
- Unix users: ./mvnw clean package
- Windows users: mvnw.cmd clean package

## To run the app in windows.
- java -Dcolored=false -jar target/hitman-cli-1.0-SNAPSHOT-jar-with-dependencies.jar

## To run app with colors in unix based system.
- java -jar target/hitman-cli-1.0-SNAPSHOT-jar-with-dependencies.jar

## To get colorful fonts in windows
- Directly run the main class in 'IntelliJ'. (They have support for ascii color codes)
