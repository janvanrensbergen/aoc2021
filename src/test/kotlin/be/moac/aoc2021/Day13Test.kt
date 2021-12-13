package be.moac.aoc2021

import be.moac.aoc2021.Day13.Instruction.X
import be.moac.aoc2021.Day13.Instruction.Y
import be.moac.aoc2021.Day13.instructions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0",
            "",
            "fold along y=7",
            "fold along x=5",
        )

        val result = Day13 partOne input

        assertThat(result).isEqualTo(17L)
    }

    @Test
    fun `parse instructions`() {

        assertThat(listOf("   ", "bogus", "fold along y=7").instructions()).containsExactly(Y(7))
        assertThat(listOf("   ", "bogus", "fold along y=7", "fold along x=5").instructions()).containsExactly(Y(7), X(5))
        assertThat(listOf("   ", "bogus", "fold along y=655", "fold along x=447").instructions()).containsExactly(Y(655), X(447))


    }

    @Test
    fun `part two`() {

        val input = listOf(
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0",
            "",
            "fold along y=7",
            "fold along x=5",
        )

        val result = Day13 partTwo input

        assertThat(result).isEqualTo(0L)
    }

}

