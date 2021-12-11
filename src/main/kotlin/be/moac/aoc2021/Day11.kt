package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day11_input.txt".readLines()

    println("Part one: ${timed { Day11 partOne input }}")
    println("Part two: ${timed { Day11 partTwo input }}")

}

object Day11 {

    infix fun partOne(input: List<String>): Long =
        input.parse().cycle(100).flashes

    infix fun partTwo(input: List<String>): Long {
        tailrec fun repeat(octopusses: Octopusses, step: Long = 0): Long =
            when (octopusses.size) {
                octopusses.count { it.value == 0} -> step
                else -> repeat(octopusses.cycleInternal(), step + 1)
            }

        return repeat(input.parse())
    }

    fun Octopusses.cycle(times: Int = 1): Result {
        tailrec fun repeat(octopusses: Octopusses, flashes: Long = 0L , index: Int = 1): Result =
            when (index) {
                times -> Result(octopusses, flashes)
                else -> {
                    val flashed = octopusses.cycleInternal()
                    repeat(flashed, flashes + flashed.count { it.value == 0 }, index + 1)
                }
            }

        return repeat(cycleInternal())
    }

    private fun Octopusses.cycleInternal(): Octopusses {
        tailrec fun flash(octopusses: Octopusses): Octopusses {
            return when {
                octopusses.none { it.value > 9 } -> octopusses
                else -> {
                    val position = octopusses.filter { it.value > 9 }.map { it.key }.first()
                    val adjacent = position.adjacent()
                    val result = octopusses.map {
                        when {
                            adjacent.contains(it.key) && it.value != 0 -> it.key to it.value + 1
                            it.key == position -> it.key to 0
                            else -> it.key to it.value
                        }
                    }.toMap()
                    flash(result)
                }
            }
        }
        return flash(plus(1))
    }

    fun Octopusses.plus(x: Int = 1) = this.map { it.key to it.value.plus(x) }.toMap()

    fun Octopusses.print(): String =
        (0..this.keys.maxOf { it.y }).map { y ->
            (0..this.keys.maxOf { it.x }).map { x -> this[Position(x, y)].toString() }.fold("") { acc, s -> "$acc$s" }
        }
            .fold("") { acc, s -> "$acc\n$s" }

    fun List<String>.parse(): Octopusses =
        this.flatMapIndexed { y, line -> line.mapIndexed { x, c -> Position(x, y) to c.digitToInt() } }.toMap()

    data class Position(val x: Int, val y: Int) {
        fun adjacent() = listOf(
            Position(x.minus(1), y.minus(1)), Position(x, y.minus(1)), Position(x.plus(1), y.minus(1)),
            Position(x.minus(1), y), Position(x.plus(1), y),
            Position(x.minus(1), y.plus(1)), Position(x, y.plus(1)), Position(x.plus(1), y.plus(1)),
        )
    }

    data class Result(val octopusses: Octopusses, val flashes: Long) {
        fun print() = octopusses.print()
    }

}

typealias Energy = Int
typealias Octopusses = Map<Day11.Position, Energy>


