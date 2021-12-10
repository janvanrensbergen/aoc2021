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

    infix fun partOne(input: List<CharArray>): Int  {
        val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        return input.mapNotNull { it.isCorrupt() }.sumOf { points[it]!! }
    }

    infix fun partTwo(input: List<CharArray>): Int = 0


    fun CharArray.isCorrupt(): Char? {
        val mapping = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')
        val stack = ArrayDeque<Char>()
        return this.firstOrNull {
            when {
                it.isOpening() -> {
                    stack.push(it);
                    false
                }
                !it.isOpening() -> {
                    mapping[stack.pop()] != it
                }
                else -> TODO()
            }
        }

    }

    private fun Char.isOpening() = "({[<".toCharArray().contains(this)

    fun <T> ArrayDeque<T>.push(element: T) = addLast(element)
    fun <T> ArrayDeque<T>.pop(): T? = this.pollLast()

}

