package be.moac.aoc2021

import kotlin.math.absoluteValue
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<Long> = "/day07_input.txt".readLines {line -> line.split(",").filterNot { it.isBlank() }.map { it.toLong() }}.first()

    println("Part one: ${timed { Day07 partOne input }}")
    println("Part two: ${timed(20) { Day07 partTwo input }}")
}

object Day07 {

    infix fun partOne(input: List<Long>): Long =
        input.asSequence().map { x -> input.sumOf { it.minus(x).absoluteValue } }.minOf { it }

    infix fun partTwo(input: List<Long>): Long =
        input.range().asSequence()
            .map { x -> input.sumOf { it calculateFuelTo x } }
            .minOf { it }

    infix fun Long.calculateFuelTo(x: Long): Long = with(this.minus(x).absoluteValue) { this.times(this.plus(1)).div(2) }

    private fun List<Long>.range() = (this.minOf { it }..this.maxOf { it })
}
