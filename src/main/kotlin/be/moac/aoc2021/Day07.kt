package be.moac.aoc2021

import kotlin.math.absoluteValue
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<Long> = "/day07_input.txt".readLines<String>().first().split(",").filterNot { it.isBlank() }.map { it.toLong() }

    println("Part one: ${timed { Day07 partOne input}}")
    println("Part two: ${timed(2) { Day07 partTwo input}}")
}

object Day07 {

    infix fun partOne(input: List<Long>): Long =
        input.fold(listOf<Long>()) { acc, i -> listOf(*acc.toTypedArray(), input.sumOf { it.minus(i).absoluteValue }) }.minOf { it }

    infix fun partTwo(input: List<Long>): Long =
        (input.minOf { it } .. input.maxOf { it })
            .fold(listOf<Long>()) { acc, i -> listOf(*acc.toTypedArray(), input.sumOf { it calculateFuelTo  i }) }.minOf { it }

    infix fun Long.calculateFuelTo(x: Long): Long = (0 .. this.minus(x).absoluteValue).sum()

}
