package be.moac.aoc2021

import java.util.*
import kotlin.math.absoluteValue
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day13_input.txt".readLines()

    println("Part one: ${timed(1) { Day13 partOne input }}")
    println("Part two: ${timed(0) { Day13 partTwo input }}")

}

object Day13 {
    private val regex = "fold along ([x,y])=(\\d+)".toRegex()

    internal infix fun partOne(input: List<String>): Long {
        val points = input.points()
        val instructions = input.instructions()
        return points.fold(instructions.first()).count().toLong()
    }

    infix fun partTwo(input: List<String>): Long {
        val points = input.points()
        val instructions = ArrayDeque(input.instructions())

        tailrec fun repeat(instruction: Instruction, result: List<Point>): List<Point> {
            return when {
                instructions.isEmpty() -> result.fold(instruction)
                else -> {
                    repeat(instructions.pop(), result.fold(instruction))
                }
            }
        }

        repeat(instructions.pop(), points).print()

        return 0L
    }

    private fun List<Point>.fold(instruction: Instruction) =
        when (instruction) {
            is Instruction.X ->
                this.filter { it.x < instruction.value } +
                    this.filter { it.x > instruction.value }.map { Point(it.x.minus(instruction.value.times(2)).absoluteValue, it.y) }
            is Instruction.Y ->
                filter { it.y < instruction.value } +
                    filter { it.y > instruction.value }.map { Point(it.x, it.y.minus(instruction.value.times(2)).absoluteValue) }
        }


    internal fun List<String>.points() = this
        .filterNot { it.startsWith("fold") }
        .filterNot { it.isBlank() }
        .map { with(it.split(",")) { Point(this.first().toInt(), this.last().toInt()) } }

    internal fun List<String>.instructions() =
        this
            .filter { regex.matches(it) }
            .map {
                val (direction, value) = regex.find(it)!!.destructured
                when (direction) {
                    "x" -> Instruction.X(value.toInt())
                    else -> Instruction.Y(value.toInt())
                }
            }

    sealed class Instruction(open val value: Int) {
        data class X(override val value: Int) : Instruction(value)
        data class Y(override val value: Int) : Instruction(value)
    }

    internal data class Point(val x: Int, val y: Int)

    private fun List<Point>.print() {
        this.string().forEach { println(it) }
    }

    private fun List<Point>.string() =
        (0..this.maxOf { it.y }).map { y ->
            (0..this.maxOf { it.x })
                .joinToString(separator = "") { x ->
                    when {
                        this.contains(Point(x, y)) -> "#"
                        else -> " "
                    }
                }
        }

}

