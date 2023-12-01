package day01

import println
import readInput

fun main() {

    fun calcCalibrationVal(input: String): Int {
        var firstDigit: Char? = null
        var lastDigit: Char? = null

        for (i in input.indices) {
            if (input[i].isDigit()) {
                firstDigit = input[i]
                break;
            }
        }

        for (i in input.length - 1 downTo 0) {
            if (input[i].isDigit()) {
                lastDigit = input[i]
                break;
            }
        }

        if (firstDigit != null && lastDigit != null) {
            val combined: String = firstDigit.toString() + lastDigit.toString()
            return combined.toInt()
        }

        return 0
    }

    fun findNumbers(input: List<String>): Int {
        var sum = 0;
        for (item in input) {
            sum += calcCalibrationVal(item)
        }

        return sum;
    }

    val input = readInput("day01/Day01")
    findNumbers(input).println()
}
