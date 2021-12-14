package be.moac.aoc2021

import java.util.*
import kotlin.math.absoluteValue
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day14_input.txt".readLines()

    println("Part one: ${timed(1) { Day14 partOne input }}")
    println("Part two: ${timed(0) { Day14 partTwo input }}")

}

object Day14 {
    private val templateRegex = "^([A-Z]+)".toRegex()
    private val ruleRegex = "([A-Z]{2})\\s->\\s([A-Z])".toRegex()

    internal infix fun partOne(input: List<String>): Long {
        val template = input.polymerTemplate()
        val rules = input.rules()

        val result = template
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .apply(rules)
            .groupingBy { it }
            .eachCount()

        val maxOf = result.maxOf { it.value }
        val minOf = result.minOf { it.value }

        return maxOf.minus(minOf).toLong()
    }

    fun Template.apply(rules: Map<String, String>):String {
        tailrec fun repeat(zip: List<Pair<Char,Char>>, result: String = "", last: Char = 'x'): String =
            when{
                zip.isEmpty() -> "$result$last"
                else -> {
                    val (a, b) = zip.first()
                    val newResult = rules["$a$b"]?.let { "$a$it" } ?: "$a"
                    repeat(zip.drop(1), result + newResult, b)
                }
            }

       return repeat(this.zipWithNext())
    }

    infix fun partTwo(input: List<String>): Long {
        return 0L
    }


    internal fun List<String>.polymerTemplate() =
        this.first { templateRegex.matches(it) }

    internal fun List<String>.rules():Map<String, String> =
        this.filter { ruleRegex.matches(it) }
            .map { ruleRegex.find(it) }
            .associate { it!!.groupValues[1] to it.groupValues[2] }
}

private typealias Template = String

