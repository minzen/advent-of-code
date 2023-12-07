package day06

import println
import readInput

fun main() {
    // read input to a list
    val input = readInput("day06/input")

    fun readWinningTime(times: String): Long {
        val rowItems = times.split(":")
        val timeItemOnRow = rowItems[1].replace(" ", "").trim()
        return timeItemOnRow.toLong()
    }

    fun readWinningDistance(distances: String): Long {
        val rowItems = distances.split(":")
        val distanceItemOnRow = rowItems[1].replace(" ", "").trim()
        return distanceItemOnRow.toLong()
    }

    fun computeRaceScenarios(winningTime: Long, winningDistance: Long): List<Long> {
        var winningDistances: MutableList<Long> = mutableListOf()
        // Initial speed
        var toyBoatSpeed = 0L
        var distanceTravelled = 0L
        var timeRemaining = winningTime

        // Iterate the various scenarios in a loop
        // and compute the distance travelled.
        // In case the distance is higher, add it to the list of winning distances
        while (timeRemaining > 0L) {
            distanceTravelled = toyBoatSpeed * timeRemaining
            if (distanceTravelled > winningDistance) {
//                println("Toyboat speed: $toyBoatSpeed, timeRemaining: $timeRemaining = $distanceTravelled")
                winningDistances.add(distanceTravelled)
            }
            toyBoatSpeed++
            timeRemaining--
        }
        return winningDistances
    }

    val winningTimes = readWinningTime(input[0])
    winningTimes.println()

    val raceDistances = readWinningDistance(input[1])
    raceDistances.println()

    val winningEntries = computeRaceScenarios(winningTimes, raceDistances)
    println("winningEntries: " +winningEntries.size)

//    var productOfWinningEntries = winningEntries.reduce( { acc, next -> acc * next });
//    println("Product of winning entries: $productOfWinningEntries")
}
