package be.moac.aoc2021

import be.moac.aoc2021.Day07.calculateFuelTo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day07Test {

    @Test
    internal fun `part one`() {

        val input = listOf(16L,1L,2L,0L,4L,2L,7L,1L,2L,14L)
        
        val result = Day07 partOne input

        assertThat(result).isEqualTo(37L)
    }

    @Test
    internal fun `part two`() {

        val input = listOf(16L,1L,2L,0L,4L,2L,7L,1L,2L,14L)

        val result = Day07 partTwo input

        assertThat(result).isEqualTo(168L)
    }

    @Test
    internal fun `calculate fuel`() {
        assertThat(16L calculateFuelTo 5).isEqualTo(66)
        assertThat(1L calculateFuelTo 5).isEqualTo(10)
        assertThat(2L calculateFuelTo 5).isEqualTo(6)
    }
}
