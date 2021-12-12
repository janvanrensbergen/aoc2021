package be.moac.aoc2021

fun main() {
    val input: List<String> = "/day02_input.txt".readLines()

    println("Part one: ${Day02 partOne input}")
    println("Part two: ${Day02 partTwo input}")
}

object Day02 {

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


    infix fun partTwo(input: List<String>) =
        input.fold(Position()) { position, s ->
            with(s.split(" ")) {
                when (this.first()) {
                    "forward" -> position.forward(this.last().toLong())
                    "down" -> position.down(this.last().toLong())
                    "up" -> position.up(this.last().toLong())
                    else -> position
                }
            }
        }.result()

    data class Position(val horizontal: Long = 0L, val depth: Long = 0L, val aim: Long = 0L) {

        fun down(x: Long) = Position(horizontal = horizontal, depth = depth, aim = aim.plus(x))
        fun up(x: Long) = Position(horizontal = horizontal, depth = depth, aim = aim.minus(x))
        fun forward(x: Long) = Position(horizontal = horizontal.plus(x), depth = depth.plus(aim.times(x)), aim = aim)

        fun result() = horizontal.times(depth)
    }

}
