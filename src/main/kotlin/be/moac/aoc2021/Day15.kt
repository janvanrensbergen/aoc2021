package be.moac.aoc2021

import java.util.*
import kotlin.Comparator
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day15_input.txt".readLines()

    println("Part one: ${timed(0) { Day15 partOne input }}")
    println("Part two: ${timed(0) { Day15 partTwo input }}")

}

object Day15 {

    internal infix fun partOne(input: List<String>): Long {
        val wall = input.parse()
        val destination = Point(wall.keys.maxOf { it.x }, wall.keys.maxOf { it.y })
        return wall.dijkstra(destination = destination).toLong()
    }


    infix fun partTwo(input: List<String>): Long {
        val wall = input.parse().expand()
        val destination = Point(wall.keys.maxOf { it.x }, wall.keys.maxOf { it.y })
        return wall.dijkstra(destination = destination).toLong()
    }

    private fun Wall.dijkstra(start: Point = Point(0,0), destination: Point): Int {
        val queue = PriorityQueue(DistanceComparator())
        val visited = hashSetOf<Point>()
        val risks = hashMapOf<Point, Int>()

        risks[start] = 0
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (point, risk) = queue.remove()
            visited.add(point)

            if (risks.getOrDefault(point, Int.MAX_VALUE) < risk) continue

            point.adjacent()
                .filter { this.containsKey(it) }
                .filterNot { visited.contains(it) }
                .forEach { neighbour ->
                    val newRisk = risks.getOrDefault(point, Int.MAX_VALUE) + this[neighbour]!!
                    if (newRisk < risks.getOrDefault(neighbour, Int.MAX_VALUE)) {
                        risks[neighbour] = newRisk
                        queue.add(neighbour to newRisk)
                    }
                }
        }

        return risks[destination] ?: 0

    }

    private class DistanceComparator: Comparator<Pair<Point, Int>> {
        override fun compare(first: Pair<Point, Int>, second: Pair<Point, Int>): Int {
            return first.second.compareTo(second.second)
        }

    }

    internal data class Point(val x: Int, val y:Int) {
        fun adjacent(): List<Point> = listOf(
            Point(x , y.minus(1)), Point(x , y.plus(1)),
            Point(x.minus(1) , y), Point(x.plus(1) , y)
        )
    }

    private fun List<String>.parse(): Wall {
        return this.flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Point(x, y) to value.digitToInt()}
        } .toMap()
    }

    private fun Wall.expand(): Wall {
        val width = this.keys.maxOf { it.x }
        val height = this.keys.maxOf { it.y }

        return this.entries.flatMap {
            listOf(
                it.key to it.value,
                Point(it.key.x.plus(width.plus(1)) , it.key.y) to it.value.plus(1).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(2)) , it.key.y) to it.value.plus(2).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(3)) , it.key.y) to it.value.plus(3).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(4)) , it.key.y) to it.value.plus(4).let { if(it > 9) it.minus(9) else it },

                Point(it.key.x , it.key.y.plus(height.plus(1))) to it.value.plus(1).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1)) , it.key.y.plus(height.plus(1))) to it.value.plus(2).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(2)) , it.key.y.plus(height.plus(1))) to it.value.plus(3).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(3)) , it.key.y.plus(height.plus(1))) to it.value.plus(4).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(4)) , it.key.y.plus(height.plus(1))) to it.value.plus(5).let { if(it > 9) it.minus(9) else it },

                Point(it.key.x , it.key.y.plus(height.plus(1).times(2))) to it.value.plus(2).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1)) , it.key.y.plus(height.plus(1).times(2))) to it.value.plus(3).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(2)) , it.key.y.plus(height.plus(1).times(2))) to it.value.plus(4).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(3)) , it.key.y.plus(height.plus(1).times(2))) to it.value.plus(5).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(4)) , it.key.y.plus(height.plus(1).times(2))) to it.value.plus(6).let { if(it > 9) it.minus(9) else it },

                Point(it.key.x , it.key.y.plus(height.plus(1).times(3))) to it.value.plus(3).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1)) , it.key.y.plus(height.plus(1).times(3))) to it.value.plus(4).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(2)) , it.key.y.plus(height.plus(1).times(3))) to it.value.plus(5).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(3)) , it.key.y.plus(height.plus(1).times(3))) to it.value.plus(6).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(4)) , it.key.y.plus(height.plus(1).times(3))) to it.value.plus(7).let { if(it > 9) it.minus(9) else it },

                Point(it.key.x , it.key.y.plus(height.plus(1).times(4))) to it.value.plus(4).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1)) , it.key.y.plus(height.plus(1).times(4))) to it.value.plus(5).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(2)) , it.key.y.plus(height.plus(1).times(4))) to it.value.plus(6).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(3)) , it.key.y.plus(height.plus(1).times(4))) to it.value.plus(7).let { if(it > 9) it.minus(9) else it },
                Point(it.key.x.plus(width.plus(1).times(4)) , it.key.y.plus(height.plus(1).times(4))) to it.value.plus(8).let { if(it > 9) it.minus(9) else it },

                

            )
        }.toMap()
    }

    private fun Wall.print() =
        (0..this.keys.maxOf { it.y }).forEach { y ->
            val line = (0..this.keys.maxOf { it.x }).joinToString(separator = "") { x -> this[Point(x, y)].toString() }
            println(line)
        }
    
}

private typealias Wall = Map<Day15.Point, Int>



