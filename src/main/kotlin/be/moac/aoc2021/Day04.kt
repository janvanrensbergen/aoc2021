package be.moac.aoc2021

fun main() {
    val input: List<String> = "/day04_input.txt".readLines()

    println("Part one: ${Day04 partOne input}")
    println("Part two: ${Day04 partTwo input}")
}

object Day04 {

    infix fun partOne(input: List<String>): Long {
        val drawNumbers = input.first().split(",").map { it.toInt() }.toList()
        val boards = input.drop(2).parse()

        fun play(boards: List<Board>, number: Int, index: Int): Long =
            with(boards.play(number)) {
                when {
                    this.any { it.bingo() } -> this.first { it.bingo() }.result(number)
                    else -> play(this, drawNumbers[index], index.plus(1))
                }
            }

        return play(boards, drawNumbers[0], 1)
    }
    
    infix fun partTwo(input: List<String>): Long {
        val drawNumbers = input.first().split(",").map { it.toInt() }.toList()
        val boards = input.drop(2).parse()

        fun play(boards: List<Board>, number: Int, index: Int): Long =
            with(boards.play(number)) {
                when {
                    this.size == 1 && this.first().bingo() -> this.first { it.bingo() }.result(number)
                    else -> play(this.filterNot { it.bingo() }, drawNumbers[index], index.plus(1))
                }
            }

        return play(boards, drawNumbers[0], 1)
    }

    data class Board(val numbers: List<List<Number>> = listOf()) {

        fun add(line: String) = Board(numbers = listOf(*numbers.toTypedArray(), line.parse()))

        fun mark(x: Int) = Board(numbers = numbers.map { row -> row.map { it.check(x) } })

        fun result(x: Int): Long = numbers.map { row -> row.filterNot { it.drawn } }
            .flatMap { row -> row.map { it.value } }
            .sum().times(x.toLong())

        fun bingo() = listOf(*numbers.toTypedArray(), *columns.toTypedArray()).any { x -> x.all { it.drawn } }

        private val columns: List<List<Number>>
            get() = listOf(
                numbers.map { it[0] },
                numbers.map { it[1] },
                numbers.map { it[2] },
                numbers.map { it[3] },
                numbers.map { it[4] },
            )
    }

    data class Number(val value: Int, val drawn: Boolean = false) {
        fun check(x: Int) = Number(value = value, drawn = drawn || value == x)
    }

    private fun List<Board>.play(x: Int): List<Board> = this.map { board -> board.mark(x) }

    private fun List<String>.parse(): List<Board> {
        val fold = this.fold(listOf<Board>() to Board()) { acc, line ->
            when {
                line.isBlank() -> listOf(*acc.first.toTypedArray(), acc.second) to Board()
                else -> acc.first to acc.second.add(line)
            }
        }
        return listOf(*fold.first.toTypedArray(), fold.second)
    }

    private fun String.parse() = this.split(" ")
        .filter { it.isNotBlank() }
        .map { Day04.Number(it.toInt()) }

}
