package be.moac.aoc2021

import be.moac.aoc2021.Day09.adjacent
import be.moac.aoc2021.Day09.parse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day09Test {

    @Test
    internal fun `part one`() {

        val input = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678",
        )

        val result = Day09 partOne input

        assertThat(result).isEqualTo(15)
    }

    @Test
    internal fun `part two`() {

        val input = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678",
        )

        val result = Day09 partTwo input

        assertThat(result).isEqualTo(1134)
    }

    @Test
    internal fun parse() {
        val result = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678",
        ).parse()

        assertThat(result[(0 to 0)]).isEqualTo(2)
        assertThat(result[(1 to 0)]).isEqualTo(1)
        assertThat(result[(1 to 0)]).isEqualTo(1)
    }

    @Test
    internal fun `find adjacent`() {
        val input = listOf(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678",
        ).parse()
        
        assertThat(input.adjacent(0 to 0)).containsExactlyInAnyOrder(3, 1)
        assertThat(input.adjacent(1 to 1)).containsExactlyInAnyOrder(3, 1, 8, 8)
        assertThat(input.adjacent(0 to 4)).containsExactlyInAnyOrder(8,8)
        assertThat(input.adjacent(9 to 0)).containsExactlyInAnyOrder(1,1)
        assertThat(input.adjacent(9 to 4)).containsExactlyInAnyOrder(7,9)
    }

}

