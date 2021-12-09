package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day09_input.txt".readLines()

    println("Part one: ${timed { Day09 partOne input }}")
    println("Part two: ${timed { Day09 partTwo input }}")
}

object Day09 {

    infix fun partOne(input: List<String>): Int {
        val heightMap = input.parse()
        return heightMap
            .filter { entry -> heightMap.adjacent(entry.key).all { it > entry.value } }
            .map { it.value }
            .sumOf { it.plus(1) }
    }

    infix fun partTwo(input: List<String>): Long = 0L

    fun List<String>.parse(): HeightMap =
        this.flatMapIndexed { y, s -> s.mapIndexed { x, c -> (x to y) to c.digitToInt() } }.toMap()

    fun HeightMap.adjacent(point: Point): List<Int> =
        point.adjacent().mapNotNull { this[it] }

    fun Point.adjacent(): List<Point> = listOf(
        this.first.minus(1) to this.second,
        this.first to this.second.minus(1),
        this.first to this.second.plus(1),
        this.first.plus(1) to this.second,
    )

}

typealias Point = Pair<Int, Int>
typealias HeightMap = Map<Point, Int>
