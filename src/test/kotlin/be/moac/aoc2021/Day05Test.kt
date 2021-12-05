package be.moac.aoc2021

import be.moac.aoc2021.Day05.Point
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day05Test {
    @Test
    internal fun `part one`() {

        val input = listOf(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
        )

        val result = Day05 partOne input

        assertThat(result).isEqualTo(5)
    }

    @Test
    internal fun `part two`() {

        val input = listOf(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
        )

        val result = Day05 partTwo input

        assertThat(result).isEqualTo(12)
    }

    @Test
    internal fun `point range`() {
        assertThat(Point(0, 9) to Point(5, 9)).containsExactly(Point(0,9), Point(1,9), Point(2 ,9), Point(3,9), Point(4,9), Point(5,9))
        assertThat(Point(9, 4) to Point(4, 4)).containsExactly(Point(9,4), Point(8,4), Point(7 ,4), Point(6,4), Point(5,4), Point(4,4))

        assertThat(Point(5, 0) to Point(5, 5)).containsExactly(Point(5,0),Point(5,1),Point(5,2),Point(5,3),Point(5,4),Point(5,5))
        assertThat(Point(5, 5) to Point(5, 0)).containsExactly(Point(5,5),Point(5,4),Point(5,3),Point(5,2),Point(5,1),Point(5,0))

        assertThat(Point(1, 1) to Point(3, 3)).containsExactly(Point(1,1),Point(2,2),Point(3,3))
        assertThat(Point(9, 7) to Point(7, 9)).containsExactly(Point(9,7),Point(8,8),Point(7,9))
        assertThat(Point(7, 9) to Point(9, 7)).containsExactly(Point(7,9),Point(8,8),Point(9,7))
        assertThat(Point(3, 3) to Point(1, 1)).containsExactly(Point(3,3),Point(2,2),Point(1,1))
    }
}
