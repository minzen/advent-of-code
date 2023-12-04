package day04

import println
import readInput
import kotlin.math.pow

fun main() {

    var points: MutableList<Int> = mutableListOf()
    var currentCards: MutableList<Int> = mutableListOf()
    var currentWins: MutableList<Int> = mutableListOf()

    fun countWinningCards(winningCards: List<Int>):Int {
        return winningCards.size
    }

    fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

    fun addWinningCards(winningCards: List<String>, ownCards:List<String>) {
        var wins: Int = 0
        var base: Double = 2.0

        for (oc in ownCards) {
            if (winningCards.contains(oc)) {
                wins++
            }
        }
        var roundPoints = base.pow(wins - 1)
        points.add(roundPoints.toInt())
    }

    fun splitRowToOwnAndWinningCards(input: String) {
        val identifierWithCards = input.split(":")
        val cardsPart = identifierWithCards[1]
        val cards = cardsPart.split("|")

        // Part 1 of the cards: winning numbers
        val winningCardsList = stringToWords(cards[0])
        // Part 2 of the cards: own cards
        val ownCardsList = stringToWords(cards[1])

        addWinningCards(winningCardsList, ownCardsList)
    }


    // read input to a list
    val input = readInput("day04/input")
    for (row in input) {
        splitRowToOwnAndWinningCards((row))
    }
    points.sum().println()
}
