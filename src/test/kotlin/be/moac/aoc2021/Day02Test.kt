package be.moac.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day02Test {

    @Test
    internal fun `Part one`() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        val result = Day02 partOne input

        assertThat(result).isEqualTo(150L)
    }

    @Test
    internal fun `part two`() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        val result = Day02 partTwo  input

        assertThat(result).isEqualTo(900L)
    }
}
