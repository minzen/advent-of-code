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
//            println("nrWithColor: " + nrWithColor)
            for (numberWithColor in nrWithColor) {
                val splitted = numberWithColor.split(" ")
//                println("splitted: " +splitted)
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


    // Read the content of row to a map
    fun createListOfIds(gameEntries: List<String>): List<Int> {
        var idList: MutableList<Int> = mutableListOf()

        for (row: String in gameEntries) {
            val gameWithId = row.substringBefore(':')
            val idStr = gameWithId.substringAfter(' ')
            val id = idStr.toInt()
            val rgbEntriesStr = row.substringAfter(": ")
            val rowRgbEntries = generateRowRgbEntries(rgbEntriesStr)
            //println("rowRgbEntries" + rowRgbEntries)

            if (shouldIdBeAdded(rowRgbEntries)) {
                idList.add(id)
            }
        }
        return idList
    }

    // Compute the sum of the IDs that are contained in the list
    fun sumIds(idList: List<Int>): Int {
        return idList.sum()
    }


    val input = readInput("day02/input")
    val idList = createListOfIds(input)
    sumIds(idList).println()

}
