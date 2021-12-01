package be.moac.aoc2021.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CalculateDepthTest {

    @Test
    internal fun partOne() {

        val input = listOf(199L, 200L, 208L, 210L, 200L, 207L, 240L, 269L, 260L, 263L)

        val result = CalculateDepth partOne input

        assertThat(result).isEqualTo(7L)
    }
}
