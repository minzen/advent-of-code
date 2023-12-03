package day02

import println
import readInput

fun main() {

    val cubeColorsWithAmount: Map<String, Int> = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun shouldAddToMap(color: String, number: Int, mapToCheck: MutableMap<String, Int>): Boolean {
        return mapToCheck[color]!! < number
    }

    fun generateRowRgbEntries(rowEntries: String): Map<String, Int> {
        var rgbEntries: MutableMap<String, Int> = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        val rgbRowEntriesList = rowEntries.split("; ")
        for (entry: String in rgbRowEntriesList) {
            val nrWithColor: List<String> = entry.split(", ")
            for (numberWithColor in nrWithColor) {
                val splitted = numberWithColor.split(" ")
                val colorPart = splitted[1]
                val numberPart = splitted[0].toInt()
                if (shouldAddToMap(colorPart, numberPart, rgbEntries)) {
                    rgbEntries[colorPart] = numberPart
                }
            }

        }
        return rgbEntries
    }

    fun shouldIdBeAdded(rgbMap: Map<String, Int>): Boolean {
        val numberOfRed = cubeColorsWithAmount["red"]
        val testRed = rgbMap["red"]
        val numberOfGreen = cubeColorsWithAmount["green"]
        val testGreen = rgbMap["green"]
        val numberOfBlue = cubeColorsWithAmount["blue"]
        val testBlue = rgbMap["blue"]

        if (testRed != null && numberOfRed != null) {
            if (testRed > numberOfRed) {
                return false
            }
        }
        if (testGreen != null && numberOfGreen != null) {
            if (testGreen > numberOfGreen) {
                return false
            }
        }
        if (testBlue != null && numberOfBlue != null) {
            if (testBlue > numberOfBlue) {
                return false
            }
        }

        return true
    }


    fun createListOfPowers(gameEntries: List<String>): List<Int> {
        var powerList: MutableList<Int> = mutableListOf()

        for (row: String in gameEntries) {
            val gameWithId = row.substringBefore(':')
            val idStr = gameWithId.substringAfter(' ')
            val id = idStr.toInt()
            val rgbEntriesStr = row.substringAfter(": ")
            val rowRgbEntries = generateRowRgbEntries(rgbEntriesStr)

            val rowPower = rowRgbEntries["red"]!! * rowRgbEntries["green"]!! * rowRgbEntries["blue"]!!
            powerList.add(rowPower)
        }
        return powerList
    }

    fun sumPowers(powersList: List<Int>): Int {
        return powersList.sum()
    }


    val input = readInput("day02/input")
    val powersList = createListOfPowers(input)
    sumPowers(powersList).println()

}
