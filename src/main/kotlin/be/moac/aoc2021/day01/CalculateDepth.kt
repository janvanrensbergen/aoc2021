package be.moac.aoc2021.day01

import be.moac.aoc2021.readLines

fun main() {
    val input: List<Long> = "/day01_input.txt".readLines { i -> i.toLong() }

    println("Part one: ${CalculateDepth partOne input}")
    println("Part two: ${CalculateDepth partTwo input}")
}

object CalculateDepth {

    infix fun partOne(input: List<Long>): Long =
        input.zipWithNext().fold(0)
        { acc, l -> if (l.first < l.second) acc.plus(1L) else acc }


    infix fun partTwo(input: List<Long>): Long =
        partOne((0..input.size - 3)
            .map { input.drop(it).sum() }
            .toList())

    private fun List<Long>.sum() = this[0].plus(this[1]).plus(this[2])

}
