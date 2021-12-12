package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day12_input.txt".readLines()

    println("Part one: ${timed { Day12 partOne input }}")
    println("Part two: ${timed(1) { Day12 partTwo input }}")

}

object Day12 {

    infix fun partOne(input: List<String>): Long {
        val graph = input.parse()

        fun traverse(x: String, path: Path, visited: List<String> = listOf()): List<Path> {
            return when (x) {
                "end" -> listOf(listOf(*path.toTypedArray(), x))
                else -> {
                    val newPath = listOf(*path.toTypedArray(), x)
                    val newVisited = visited.add(x)

                    val result = graph.filter { it.start == x }
                        .filterNot { newVisited.contains(it.end) }
                        .flatMap { traverse(it.end, newPath, newVisited) }

                    result.ifEmpty { listOf(newPath) }
                }
            }
        }

        return traverse("start", listOf()).filter { it.contains("end") }.size.toLong()
    }

    infix fun partTwo(input: List<String>): Long {
        val graph = input.parse()

        fun traverse(x: String, path: Path, visited: Map<String, Int> = mapOf()): List<Path> {
            return when (x) {
                "end" -> listOf(listOf(*path.toTypedArray(), x))
                else -> {
                    val newPath = listOf(*path.toTypedArray(), x)
                    val newVisited = visited.add(x)

                    val result = graph.filter { it.start == x }
                        .filterNot {
                            newVisited.any { v -> v.value == 2 } &&
                            (newVisited[it.end] ?: 0) > 0 }
                        .flatMap { traverse(it.end, newPath, newVisited) }

                    result.ifEmpty { listOf(newPath) }
                }
            }
        }

        return traverse("start", listOf())
            .filter { it.contains("end") }
            .size.toLong()
    }


    private fun List<String>.add(x: String) = if (x.all { it.isLowerCase() }) {
        listOf(*this.toTypedArray(), x)
    } else {
        this
    }

    private fun Map<String, Int>.add(x: String): Map<String, Int> = if (x.all { it.isLowerCase() }) {
        val map = this.toMutableMap()
        map[x] = this[x]?.let { it+1 } ?: 1
        map
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

private typealias Path = List<String>


