package be.moac.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06Test {

    @Test
    internal fun `part one`() {

        val input = listOf("3,4,3,1,2")

        val result = Day06 partOne input

        assertThat(result).isEqualTo(5934L)
    }

    @Test
    internal fun `part two`() {

        val input = listOf("3,4,3,1,2")

        val result = Day06 partTwo input

        assertThat(result).isEqualTo(26984457539L)
    }

    @Test
    internal fun name() {
        6.mod(3)
        (0..100).map{it.toString()}.joinToString()

        """
            
        """.trimIndent()
    }
}
