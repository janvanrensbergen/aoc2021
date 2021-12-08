package be.moac.aoc2021

import be.moac.aoc2021.Day08.Digit.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day08_input.txt".readLines()

    println("Part one: ${timed { Day08 partOne input}}")
    println("Part two: ${timed { Day08 partTwo input}}")
}

object Day08 {

    infix fun partOne(input: List<String>): Int =
        input.parse()
            .flatMap { it.ouputs }
            .count { it is One || it is Four || it is Seven || it is Eight }


    infix fun partTwo(input: List<String>): Long {
        val groupBy = input
            .map { it.split(" | ") }
            .map {
                val digits = it.first().split(" ").toDigit()
                it.last().split(" ").map { x -> "${digits[x.alphabetized()]}" }
            }
            .map { it.fold("") { acc, s -> "$acc$s" } }
            .sumOf { it.toLong() }
        return groupBy
    }


    data class Line(val inputs: List<Digit>, val ouputs: List<Digit>)

    sealed class Digit {
        object One : Digit()
        object Four : Digit()
        object Seven : Digit()
        object Eight : Digit()

        object Unknown : Digit()
    }

    private fun List<String>.parse(): List<Line> =
        this.asSequence()
            .map { it.split(" | ") }
            .map { Line(it.first().toDigits(), it.last().toDigits()) }
            .toList()

    private fun String.toDigits(): List<Digit> =
        this.split(" ").map { it.toDigit() }.toList()


    fun List<String>.toDigit(): Map<String, Int> {
        val input = this.map { it.alphabetized() }

        val one = input.first { it.length == 2 }
        val four = input.first { it.length == 4 }
        val seven = input.first { it.length == 3 }
        val eight = input.first { it.length == 7 }

        val three = input.filter { it.length == 5 }.first { seven.toCharArray().all { c -> it.toCharArray().contains(c) } }
        val nine = input.filter { it.length == 6 }.first { four.toCharArray().all { c -> it.toCharArray().contains(c) } }

        val left = eight.toCharArray().filterNot { three.toCharArray().contains(it) }


        val zero = input.filter { it.length == 6 }
            .first {
                seven.toCharArray().all { c -> it.toCharArray().contains(c) } &&
                        left.toCharArray().all { c -> it.toCharArray().contains(c) }
            }

        val six = input.asSequence()
            .filter { it.length == 6 }
            .filterNot { it == zero }
            .filterNot { it == nine }
            .first()

        val middle = eight.toCharArray().filterNot { zero.toCharArray().contains(it) }
        val topLeft = four.toCharArray()
            .filterNot { one.toCharArray().contains(it) }
            .filterNot { middle.contains(it) }
            .first()

        val five = input.filter { it.length == 5 }.first { it.toCharArray().contains(topLeft) }
        val two = input.filter { it.length == 5 }.filterNot { it == five }.filterNot { it == three }.first()

        return mapOf(
            zero to 0,
            one to 1,
            two to 2,
            three to 3,
            four to 4,
            five to 5,
            six to 6,
            seven to 7,
            eight to 8,
            nine to 9
        )
    }

    fun String.alphabetized() = String(toCharArray().apply { sort() })

    fun String.toDigit(): Digit {
        return when (this.length) {
            2 -> One
            4 -> Four
            3 -> Seven
            7 -> Eight
            else -> {
                Unknown
            }
        }
    }
}
