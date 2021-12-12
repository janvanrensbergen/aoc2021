package be.moac.aoc2021

import be.moac.aoc2021.readLines

fun main() {
    val input: List<String> = "/day03_input.txt".readLines()

    println("Part one: ${Day03 partOne input}")
    println("Part two: ${Day03 partTwo input}")
}

object Day03 {

    infix fun partOne(input: List<String>): Long {

        val map = (0 until input.first().length)
            .map { index ->
                input.map { s -> s[index] }.count { it == '1' }
            }

        val gammaRate = map.map { if (it > input.size.div(2)) '1' else '0' }.joinToString(separator = "").toLong(2)
        val epsilonRate = map.map { if (it < input.size.div(2)) '1' else '0' }.joinToString(separator = "").toLong(2)

        return gammaRate.times(epsilonRate)
    }

    infix fun partTwo(input: List<String>): Long {

        tailrec fun findOxygen(i: List<String>, position: Int, prefix: String): String {
            return if(i.size == 1) {
                i.first()
            } else {
                val p = "$prefix${i.mostCommon(position)}"
                findOxygen(i.filter { it.startsWith(p) }, position + 1, p)
            }
        }

        tailrec fun findScrubber(i: List<String>, position: Int, prefix: String): String {
            return if(i.size == 1) {
                i.first()
            } else {
                val p = "$prefix${i.leastCommon(position)}"
                findScrubber(i.filter { it.startsWith(p) }, position + 1, p)
            }
        }

        val mostCommon = input.mostCommon(0)
        val oxy = findOxygen(input.filter { it.startsWith(mostCommon) }, 1, "$mostCommon").toLong(2)

        val leastCommon = input.leastCommon(0)
        val scrub = findScrubber(input.filter { it.startsWith(leastCommon) }, 1, "$leastCommon").toLong(2)

        return oxy.times(scrub)
    }

    private fun List<String>.mostCommon(x: Int): Char {
        val countOne = this.map { it[x] }.count { it == '1' }
        val countZero = this.map { it[x] }.count { it == '0' }
        return when {
            countOne > countZero -> '1'
            countOne == countZero -> '1'
            else -> '0'
        }
    }

    private fun List<String>.leastCommon(x: Int): Char {
        return when {
            this.mostCommon(x) == '1' -> '0'
            this.mostCommon(x) == '0' -> '1'
            else -> ' '
        }
    }

}
