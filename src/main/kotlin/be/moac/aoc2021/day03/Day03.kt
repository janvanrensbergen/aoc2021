package be.moac.aoc2021.day03

import be.moac.aoc2021.readLines

fun main() {
    val input: List<String> = "/day03_input.txt".readLines()

    println("Part one: ${Day03 partOne input}")
    println("Part two: ${Day03 partTwo input}")
}

object Day03 {

    infix fun partOne(input: List<String>): Long {

        val map = (0 until input.first().length)
            .map { index ->
                input.map { s -> s[index] }.count { it == '1' }
            }

        val gammaRate = map.map { if (it > input.size.div(2)) '1' else '0' }.joinToString(separator = "").toLong(2)
        val epsilonRate = map.map { if (it < input.size.div(2)) '1' else '0' }.joinToString(separator = "").toLong(2)

        println("$gammaRate - $epsilonRate")

        return gammaRate.times(epsilonRate)
    }

    infix fun partTwo(input: List<String>) = 0L

}
