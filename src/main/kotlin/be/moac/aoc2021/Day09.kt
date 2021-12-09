package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day09_input.txt".readLines()

    println("Part one: ${timed { Day09 partOne input }}")
    println("Part two: ${timed(1) { Day09 partTwo input }}")

}

object Day09 {

    infix fun partOne(input: List<String>): Int =
        input.parse()
            .lowestPoints()
            .map { it.value }
            .sumOf { it.plus(1) }

    infix fun partTwo(input: List<String>): Int {
        return with(input.parse()) {
            val basins = lowestPoints()
                .map { this.findBasin(it.key) }

            this.print(basins.flatten())
            basins
                .map {
                    it.count()
                }
                .sortedDescending()
                .take(3)
                .reduce(Int::times)


        }
    }

    fun HeightMap.findBasin(startPoint: Point): Basin {
        val visited = mutableListOf<Point>()

        fun find(basin: Basin, point: Point): Basin {
            visited.add(point)
            return when {
                this[point]?.let { it == 9 } ?: true -> basin
                else -> {
                    val newBasin = listOf(*basin.toTypedArray(), point)
                    point.adjacent()
                        .filterNot { visited.contains(it) }
                        .flatMap { p -> find(newBasin, p) }
                        .ifEmpty { newBasin }
                }
            }
        }


        return find(listOf(), startPoint).distinct()
    }

    private fun HeightMap.print(basin: Basin = listOf()) {
        val width = this.map { it.key.first }.maxOf { it }
        val height = this.map { it.key.second }.maxOf { it }

        (0..height).forEach { y ->
            println((0..width)
                .map { x -> (x to y) }
                .map { p ->
                    when {
                        basin.contains(p) -> "."
                        this[p] == 9 -> "9"
                        else -> "$p"
//                        else -> "${this[p]}"
                    }}
                .fold("") { acc, i -> "$acc$i" })
        }
    }

    private fun HeightMap.lowestPoints() =
        this.filter { entry -> this.adjacent(entry.key).all { it > entry.value } }


    fun List<String>.parse(): HeightMap =
        this.flatMapIndexed { y, s -> s.mapIndexed { x, c -> (x to y) to c.digitToInt() } }.toMap()

    fun HeightMap.adjacent(point: Point): List<Int> =
        point.adjacent().mapNotNull { this[it] }

    fun Point.adjacent(): List<Point> = listOf(
        this.first.minus(1) to this.second,
        this.first.plus(1) to this.second,
        this.first to this.second.minus(1),
        this.first to this.second.plus(1),
    )

}

typealias Point = Pair<Int, Int>
typealias HeightMap = Map<Point, Int>
typealias Basin = List<Point>
