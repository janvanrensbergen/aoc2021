package be.moac.aoc2021

import kotlin.math.max

fun main() {
    val input: List<String> = "/day05_input.txt".readLines()

    println("Part one: ${Day05 partOne input}")
    println("Part two: ${Day05 partTwo input}")
}

object Day05 {

    infix fun partOne(input: List<String>): Long {
        return input.map { line -> line.split(" -> ") }
            .flatMap { line ->
                val startPoint = line.first().toPoint()
                val endPoint = line.last().toPoint()
                if (startPoint.x == endPoint.x || startPoint.y == endPoint.y) {
                    val up = (startPoint.x..endPoint.x).flatMap { x -> (startPoint.y..endPoint.y).map { y -> Point(x, y) } }
                    val down = (startPoint.x downTo endPoint.x).flatMap { x -> (startPoint.y downTo endPoint.y).map { y -> Point(x, y) } }
                    listOf(*up.toTypedArray(), *down.toTypedArray())
                } else {
                    listOf()
                }
            }
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .count()
            .toLong()

    }

    infix fun partTwo(input: List<String>): Long {
        return 0L
    }


    data class Point(val x: Int, val y: Int)

    private fun String.toPoint(): Point {
        val split = this.split(",")
        return Point(split.first().toInt(), split.last().toInt())
    }
}
