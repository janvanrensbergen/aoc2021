package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day12_input.txt".readLines()

    println("Part one: ${timed { Day12 partOne input }}")
    println("Part two: ${timed(10) { Day12 partTwo input }}")

}

object Day12 {

    infix fun partOne(input: List<String>): Long {
        val graph = input.parse()

        fun traverse(x: String, path: Path = "", visited: List<String> = listOf()): List<Path> =
            when {
                x == "end" -> listOf(path + x)
                visited.contains(x) -> listOf(path)
                else -> graph.filter { it.start == x }.flatMap { traverse(it.end, path + x, visited.add(x)) }
            }

        return traverse("start").filter { it.contains("end") }.size.toLong()
    }

    infix fun partTwo(input: List<String>): Long {
        val graph = input.parse()

        fun traverse(x: String, path: Path = "", visited: Map<String, Int> = mapOf()): List<Path> =
            when {
                x == "end" -> listOf(path + x)
                visited.any { v -> v.value == 2 } && (visited[x] ?: 0) > 0 -> listOf(path)
                else -> graph.filter { it.start == x }.flatMap { traverse(it.end, path + x, visited.add(x)) }
            }

        return traverse("start").filter { it.contains("end") }.size.toLong()
    }


    private fun List<String>.add(x: String) = if (x.all { it.isLowerCase() }) {
        this + x
    } else {
        this
    }

    private fun Map<String, Int>.add(x: String): Map<String, Int> =
        if (x.all { it.isLowerCase() }) {
            this + (x to (this[x]?.let { it + 1 } ?: 1))
        } else {
            this
        }

    fun List<String>.parse(): List<Connection> =
        this.flatMap {
            val split = it.split("-")
            when {
                split.last() == "end" -> listOf(Connection(split.first(), split.last()))
                split.first() == "end" -> listOf(Connection(split.last(), split.first()))
                split.first() == "start" -> listOf(Connection(split.first(), split.last()))
                split.last() == "start" -> listOf(Connection(split.last(), split.first()))
                else -> listOf(Connection(split.first(), split.last()), Connection(split.last(), split.first()))
            }
        }

    data class Connection(val start: String, val end: String)

}

private typealias Path = String


