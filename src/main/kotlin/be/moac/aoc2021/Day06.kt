package be.moac.aoc2021

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    val input: List<String> = "/day06_input.txt".readLines()

    println("Part one: ${timed { Day06 partOne input}}")
    println("Part two: ${timed { Day06 partTwo input}}")
}

object Day06 {

    infix fun partOne(input: List<String>): Long {
        val fish = input.parse()
        fun cycle(fish:List<Fish>, index: Int):List<Fish> {
            return when(index) {
                80 -> fish
                else -> {
                    val newFish = fish.filter { it.timer == 0 }.map { Fish(8) }
                    val oldFish = fish.map { it.handle() }
                    cycle(listOf(*oldFish.toTypedArray(), *newFish.toTypedArray()), index.plus(1))
                }
            }
        }

        return cycle(fish, 0).count().toLong()
    }

    infix fun partTwo(input: List<String>): Long {
        val fish = input.first().split(",").map { it.toInt() }.groupBy { it }.map { (k,v) -> k to v.size.toLong() }.toMap()

        fun cycle(fish: Map<Int, Long>, index: Int): Long {
//            println("$index - ${fish}")
            return when(index){
                256 -> fish.values.sum()
                else -> {
                    val oldFish = fish.map { (age, amount) -> when{
                        age == 0 -> 6 to amount + (fish[7]?:0)
                        age == 7 -> 6 to amount + (fish[0]?:0)
                        else -> age.minus(1) to amount
                    }}.toMap() + mapOf(8 to (fish[0]?:0L))

                    cycle(oldFish, index.plus(1))
                }
            }
        }
        return cycle(fish, 0)
    }


    data class Fish(val timer: Int) {
        fun handle():Fish = when {
            timer > 0 -> Fish(timer.minus(1))
            else -> Fish(6)
        }

    }

    private fun List<String>.parse() = this.first().split(",").map { Fish(it.toInt()) }
}
