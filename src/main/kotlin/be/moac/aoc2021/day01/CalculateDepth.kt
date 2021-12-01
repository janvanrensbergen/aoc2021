package be.moac.aoc2021.day01

import be.moac.aoc2021.readLines

fun main() {
    val input: List<Long> = "/day01_input.txt".readLines { i -> i.toLong()}
    val result = CalculateDepth partOne   input

    println(result)
}

object CalculateDepth {

    infix fun partOne(input: List<Long>): Long =
        input
        .fold(Pair<Long, Long?>(0L, null))
        { acc, l -> acc.second?.let { if (l > it) (acc.first.plus(1L) to l) else (acc.first to l) }
            ?: (0L to l) }
            .first

    infix fun partTwo(input: List<Long>): Long {

        return 0L
    }

}
