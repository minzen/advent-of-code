package day03

import println
import readInput

fun main() {

    // Compute the sum of part numbers
    fun sumPartNumbers(partNumbers: Set<Int>): Int {
        return partNumbers.sum()
    }

    // Get a list of coordinate pairs of special characters
    fun coordsOfSpecialChars(input: List<String>): List<Pair<Int, Int>> {
        var coords: MutableList<Pair<Int, Int>> = mutableListOf()
        for (r in input.indices) {
            val row = input[r]
            //println("current row " + r)
            for (c in row.indices) {
                val currentChar = row[c]
                //println("current index on row " +c)
                if (!currentChar.isDigit() && currentChar != '.' ) {
                    println("currentChar/special char: " +currentChar + ", found at: " + "[" +r +"," + c +"]")
                    val charCoordPair = Pair(first = r, second = c)
                    coords.add(charCoordPair)
                }
            }
        }

        return coords
    }

    fun isValidPosition(i: Int, j: Int, maxRow: Int, maxCol: Int): Boolean {
        if (i < 0 || j < 0 || i > maxRow - 1 || j > maxCol - 1) {
            return false
        }
        return true
    }

    fun getAdjacentCoords(specialCharAt: Pair<Int, Int>, input: List<String>): MutableList<Pair<Int, Int>> {
        var adjacentCoords: MutableList<Pair<Int, Int>> = mutableListOf()

        val rowsMaxIndex = input.size
        val colsMaxIndex = input[0].length
        val charAtRow = specialCharAt.first
        val charAtCol = specialCharAt.second

        // Previous row, col - 1
        if (isValidPosition(charAtRow - 1, charAtCol - 1, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow - 1][charAtCol - 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow - 1, second = charAtCol - 1))
            }
        }
        // Previous row, same col
        if (isValidPosition(charAtRow - 1, charAtCol, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow - 1][charAtCol].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow - 1, second = charAtCol))
            }
        }
        // Previous row, col + 1
        if (isValidPosition(charAtRow - 1, charAtCol + 1, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow - 1][charAtCol + 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow - 1, second = charAtCol + 1))
            }
        }
        // Same row, col - 1
        if (isValidPosition(charAtRow, charAtCol - 1, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow][charAtCol - 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow, second = charAtCol - 1))
            }
        }
        // Same row, col + 1
        if (isValidPosition(charAtRow , charAtCol + 1, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow][charAtCol + 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow, second = charAtCol + 1))
            }
        }
        // Next row, col - 1
        if (isValidPosition(charAtRow + 1, charAtCol - 1, rowsMaxIndex, colsMaxIndex)) {
            println(input[charAtRow + 1][charAtCol - 1])
            if (input[charAtRow + 1][charAtCol - 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow + 1, second = charAtCol - 1))
            }
        }
        // Next row, same col
        if (isValidPosition(charAtRow + 1, charAtCol, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow + 1][charAtCol].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow + 1, second = charAtCol))
            }
        }
        // Next row, col + 1
        if (isValidPosition(charAtRow + 1, charAtCol + 1, rowsMaxIndex, colsMaxIndex)) {
            if (input[charAtRow + 1][charAtCol + 1].isDigit()) {
                adjacentCoords.add(Pair(first = charAtRow + 1, second = charAtCol + 1))
            }
        }

        adjacentCoords.println()
        return adjacentCoords
    }

    fun extractNumberAroundIndex(input: String, indexWithDigit: Int): Int? {
        if (indexWithDigit < 0 || indexWithDigit >= input.length || !input[indexWithDigit].isDigit()) {
            return null
        }

        var startIndex = indexWithDigit
        while (startIndex > 0 && input[startIndex - 1].isDigit()) {
            startIndex--
        }

        var endIndex = indexWithDigit
        while (endIndex < input.length - 1 && input[endIndex + 1].isDigit()) {
            endIndex++
        }

        return input.substring(startIndex, endIndex + 1).toInt()
    }

    fun fetchAdjacentPartNumbers(input: List<String>, targetCoords: List<Pair<Int, Int>>): MutableSet<Int> {
        var partNumbers: MutableSet<Int> = mutableSetOf()

        for (coord in targetCoords) {
            val rowIndex = coord.first
            val colIndex = coord.second
            println(input[rowIndex])
//            println("Input at row, col " + input[rowIndex][colIndex])
            val extractedNumber = extractNumberAroundIndex(input[rowIndex], colIndex)
//            println("extracted: " + extractedNumber)
            if (extractedNumber != null) {
                partNumbers.add(extractedNumber)
            }
        }

        partNumbers.sorted().println()
        return partNumbers
    }

    // Fetches the part numbers adjacent to the special characters
    fun computeSumOfAdjacentPartNumbers(input: List<String>, specialCharCoords: List<Pair<Int, Int>>): Int {
        val partNumbers: Set<Int>
        var adjacentCoords: MutableList<Pair<Int, Int>> = mutableListOf()

        for (coord in specialCharCoords.indices) {
            var adjCoords = getAdjacentCoords(specialCharCoords[coord], input)
            adjacentCoords.addAll(adjCoords)
        }

        partNumbers = fetchAdjacentPartNumbers(input, adjacentCoords)
        return sumPartNumbers(partNumbers)
    }



    // read input to a list
    val input = readInput("day03/input")

    // get the indices of special characters
    var coordsSpecialChars = coordsOfSpecialChars(input)
    coordsSpecialChars.println()

    computeSumOfAdjacentPartNumbers(input, coordsSpecialChars).println()
}
