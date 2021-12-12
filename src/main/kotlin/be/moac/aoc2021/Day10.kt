package be.moac.aoc2021

import java.util.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<CharArray> = "/day10_input.txt".readLines { it.toCharArray() }

    println("Part one: ${timed { Day10 partOne input }}")
    println("Part two: ${timed(1) { Day10 partTwo input }}")

}

object Day10 {
    private val mapping = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')

    infix fun partOne(input: List<CharArray>): Int  {
        val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        return input.mapNotNull { it.isCorrupt() }.sumOf { points[it]!! }
    }

    infix fun partTwo(input: List<CharArray>): Long {
        val sorted = input.filter { it.isCorrupt() == null }
            .map { it.complete() }
            .map { it.calculatePoints() }
            .sorted()

        return sorted[sorted.size/2]
    }

    fun String.calculatePoints(): Long {
        val points = mapOf(')' to 1L, ']' to 2L, '}' to 3L, '>' to 4L)
        return this.fold(0L) { acc, c ->
            acc.times(5L).plus(points[c] ?: 0L)
        }
    }

    fun CharArray.complete(): String {
        val stack = ArrayDeque<Char>()
        this.forEach { c ->
            when {
                c.isOpening() -> stack.push(c)
                else -> stack.pop()
            }
        }
        return stack.map { mapping[it]!! }.joinToString(separator = "")
    }


    fun CharArray.isCorrupt(): Char? {
        val stack = ArrayDeque<Char>()
        return this.firstOrNull {
            when {
                it.isOpening() -> {
                    stack.push(it);
                    false
                }
                !it.isOpening() -> mapping[stack.pop()] != it
                else -> false
            }
        }

    }

    private fun Char.isOpening() = "({[<".toCharArray().contains(this)

}

