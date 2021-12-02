package be.moac.aoc2021.day02

import be.moac.aoc2021.readLines

fun main() {
    val input: List<String> = "/day02_input.txt".readLines()

    println("Part one: ${Pilot partOne input}")
    println("Part two: ${Pilot partTwo input}")
}

object Pilot {

    infix fun partOne(input: List<String>) =
        with(input.fold(0L to 0L) { acc, s ->
            with(s.split(" ")) {
                when (this.first()) {
                    "forward" -> acc.first.plus(this.last().toLong()) to acc.second
                    "down" -> acc.first to acc.second.plus(this.last().toLong())
                    "up" -> acc.first to acc.second.minus(this.last().toLong())
                    else -> acc.first to acc.second
                }
            }
        }) {
            first.times(second)
        }


    infix fun partTwo(input: List<String>): Long {
        return 0L
    }


}
