package day06

import println
import readInput

fun main() {

    var numberOfWinningCombinations: MutableList<Int> = mutableListOf()
    // read input to a list
    val input = readInput("day06/input")

    // The input file describes the following items
    // Time: the duration of a race in ms,
    // Distance: the record distance travelled
    // The boat's speed increases by one mm per millisecond, when the charge button is pushed

    // How long the button is pushed - compute the various scenarios for winning
    // Speed travelled per round: +1
    // At the same time the time available -1

    // Distance travelled: speed * timeavailable
    // In which cases is the toy boat winning?
    // Multiply the number of ways one may beat the record
    fun readWinningTimes(times: String): List<Int> {
        val rowItems = times.split(":")
        val timeItemsOnRow = rowItems[1].trim()
        return timeItemsOnRow.split("\\s+".toRegex()).map { s: String -> s.toInt() }
    }

    fun readWinningDistances(distances: String): List<Int> {
        val rowItems = distances.split(":")
        val distanceItemsOnRow = rowItems[1].trim()
        return distanceItemsOnRow.split("\\s+".toRegex()).map { s: String -> s.toInt() }
    }

    fun computeRaceScenarios(winningTime: Int, winningDistance: Int): List<Int> {
        var winningDistances: MutableList<Int> = mutableListOf()
        // Initial speed
        var toyBoatSpeed = 0
        var distanceTravelled = 0
        var timeRemaining = winningTime

        // Iterate the various scenarios in a loop
        // and compute the distance travelled.
        // In case the distance is higher, add it to the list of winning distances
        while (timeRemaining > 0) {
            distanceTravelled = toyBoatSpeed * timeRemaining
            if (distanceTravelled > winningDistance) {
                println("Toyboat speed: $toyBoatSpeed, timeRemaining: $timeRemaining = $distanceTravelled")
                winningDistances.add(distanceTravelled)
            }
            toyBoatSpeed++
            timeRemaining--
        }
        return winningDistances
    }

    val winningTimes = readWinningTimes(input[0])
    winningTimes.println()

    val raceDistances = readWinningDistances(input[1])
    raceDistances.println()

    for (raceEntry in winningTimes.indices) {
        val winningEntries = computeRaceScenarios(winningTimes[raceEntry], raceDistances[raceEntry])
        numberOfWinningCombinations.add(winningEntries.count())
    }

    var productOfWinningEntries = numberOfWinningCombinations.reduce( { acc, next -> acc * next });
    println("Product of winning entries: $productOfWinningEntries")
}
