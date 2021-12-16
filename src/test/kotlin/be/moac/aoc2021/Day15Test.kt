package be.moac.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581",
        )

        val result = Day15 partOne input

        assertThat(result).isEqualTo(40L)
    }

    @Test
    fun `part two`() {

        val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581",
        )

        val result = Day15 partTwo input

        assertThat(result).isEqualTo(315L)
    }


}

