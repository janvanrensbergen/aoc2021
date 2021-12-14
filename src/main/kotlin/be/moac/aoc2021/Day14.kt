package be.moac.aoc2021

import kotlin.math.ceil
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day14_input.txt".readLines()

    println("Part one: ${timed { Day14 partOne input }}")
    println("Part two: ${timed { Day14 partTwo input }}")

}

object Day14 {
    private val templateRegex = "^([A-Z]+)".toRegex()
    private val ruleRegex = "([A-Z]{2})\\s->\\s([A-Z])".toRegex()

    internal infix fun partOne(input: List<String>): Long {
        val template = input.polymerTemplate()
        val rules = input.rules()

        val result = template.apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules)

        val sum = result.sum()
        return sum.maxOf { it.value }.minus(sum.minOf { it.value })
    }

    infix fun partTwo(input: List<String>): Long {
        val template = input.polymerTemplate()
        val rules = input.rules()
        val result = template
            .apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules)
            .apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules)
            .apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules)
            .apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules).apply(rules)

        val sum = result.sum()

        val maxOf = sum.maxOf { it.value }
        val minOf = sum.minOf { it.value }

        return maxOf.minus(minOf)
    }

    private fun PolymerTemplate.apply(rules: Rules): PolymerTemplate {

        tailrec fun repeat(input: MutableTemplate, result: MutableTemplate = mutableMapOf()): PolymerTemplate =
            when {
                input.isEmpty() -> result.toMap()
                else -> {
                    val key = input.keys.first()
                    val value = input[key]!!
                    val c = rules[key]!!

                    val first = (key.first to c)
                    val second = (c to key.second)

                    result[first] = result[first]?.plus(value) ?: value
                    result[second] = result[second]?.plus(value) ?: value

                    input.remove(key)
                    repeat(input, result)
                }
            }

        return repeat(this.toMutableMap())
    }

    private fun PolymerTemplate.sum() = entries
        .foldIndexed(mutableMapOf<Char, Long>()) { index, acc, entry ->
            acc[entry.key.first] = acc[entry.key.first]?.plus(entry.value) ?: entry.value
            acc[entry.key.second] = acc[entry.key.second]?.plus(entry.value) ?: entry.value
            acc
        }
        .map { it.key to ceil(it.value.div(2.0)).toLong() }
        .toMap()

    private fun List<String>.polymerTemplate(): PolymerTemplate =
        this.first { templateRegex.matches(it) }
            .zipWithNext()
            .fold(mutableMapOf()) { acc, pair ->
                acc[pair] = acc[pair]?.plus(1L) ?: 1L
                acc
            }

    internal fun List<String>.rules(): Rules =
        this.filter { ruleRegex.matches(it) }
            .map { ruleRegex.find(it) }
            .associate { (it!!.groupValues[1].first() to it.groupValues[1].last()) to it.groupValues[2].first() }
}

private typealias PolymerTemplate = Map<Pair<Char, Char>, Long>
private typealias MutableTemplate =  MutableMap<Pair<Char, Char>, Long>
private typealias Rules = Map<Pair<Char, Char>, Char>

