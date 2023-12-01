package day01

import println
import readInput

fun main() {

    var digitNumberMap: Map<String, Char> = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9'
    )

    fun findFirst(input: String): Char? {
        for (i in input.indices) {
            if (input[i].isDigit()) {
                return input[i]
            }
            else {
                val entry = input.substring(0, i + 1)
                for ((k, v) in digitNumberMap) {
                    if (entry.contains(k)) {
                        val numberItem = digitNumberMap[k]
                        if (numberItem != null) {
                            return numberItem
                        }
                    }
                }
            }
        }
        return null
    }

    fun findLast(input: String): Char? {
        for (i in input.length - 1 downTo 0) {
            if (input[i].isDigit()) {
                return input[i]
            }
            // Ellei, tutkitaan, onko ko. indeksistä loppuun luvun sanamuotoinen esiintymä
            else {
                val entry = input.substring(i)
                for ((k, v) in digitNumberMap) {
                    if (entry.contains(k)) {
                        val numberItem = digitNumberMap[k]
                        if (numberItem != null) {
                            return numberItem
                        }
                    }
                }
            }
        }

        return null
    }

    fun calcCalibrationVal(input: String): Int? {
        var firstDigit: Char? = null
        var lastDigit: Char? = null

        firstDigit = findFirst(input)
        lastDigit = findLast(input)

        if (firstDigit != null && lastDigit != null) {
            val combined: String = firstDigit.toString() + lastDigit.toString()
            return combined.toInt()
        }

        return null
    }

    fun findNumbers(input: List<String>): Int {
        var sum = 0
        println("items: "  + input.size)
        for (item in input) {
            val res = calcCalibrationVal(item)
            if (res != null) {
                sum += res
            }
        }
        return sum
    }

    val input = readInput("day01/Day01")
    findNumbers(input).println()
}
