package be.moac.aoc2021

import be.moac.aoc2021.Day11.Position
import be.moac.aoc2021.Day11.cycle
import be.moac.aoc2021.Day11.parse
import be.moac.aoc2021.Day11.print
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526",
        )

        val result = Day11 partOne input

        assertThat(result).isEqualTo(1656L)
    }

    @Test
    fun `part two`() {

        val input = listOf(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526",
        )

        val result = Day11 partTwo input

        assertThat(result).isEqualTo(195)
    }


    @Test
    fun `find adjacent points`() {
        assertThat(Position(0, 0).adjacent()).containsExactly(
            Position(-1, -1), Position(0, -1), Position(1, -1),
            Position(-1, 0),                       Position(1, 0),
            Position(-1, 1), Position(0, 1),   Position(1, 1),
        )

        assertThat(Position(8, 7).adjacent()).containsExactly(
            Position(7, 6), Position(8, 6),    Position(9, 6),
            Position(7, 7),                       Position(9, 7),
            Position(7, 8), Position(8, 8),   Position(9, 8),
        )
    }

    @Test
    fun cycle() {

      val before = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """.trimIndent()
          .lines().parse()

        val result = before.cycle().print()

        assertThat(result).isEqualToIgnoringWhitespace("""
            6594254334
            3856965822
            6375667284
            7252447257
            7468496589
            5278635756
            3287952832
            7993992245
            5957959665
            6394862637 
        """.trimIndent())
    }

    @Test
    fun `cycle with flash`() {

        val before = """
            11111
            19991
            19191
            19991
            11111
            """.trimIndent()
            .lines().parse()

        val result = before.cycle().print()

        assertThat(result).isEqualToIgnoringWhitespace("""
            34543
            40004
            50005
            40004
            34543
        """.trimIndent())
    }

    @Test
    fun `cycle with flash 1`() {

        val before = """
            6594254334
            3856965822
            6375667284
            7252447257
            7468496589
            5278635756
            3287952832
            7993992245
            5957959665
            6394862637
            """.trimIndent()
            .lines().parse()

        val result = before.cycle().print()

        assertThat(result).isEqualToIgnoringWhitespace("""
            8807476555
            5089087054
            8597889608
            8485769600
            8700908800
            6600088989
            6800005943
            0000007456
            9000000876
            8700006848
        """.trimIndent())
    }

    @Test
    fun `cycle 10 times`() {

        val before = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """.trimIndent()
            .lines().parse()

        val result = before.cycle(10).print()

        assertThat(result).isEqualToIgnoringWhitespace("""
            0481112976
            0031112009
            0041112504
            0081111406
            0099111306
            0093511233
            0442361130
            5532252350
            0532250600
            0032240000
        """.trimIndent())
    }

    @Test
    fun `cycle 10 times results in 204 flashes`() {

        val before = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """.trimIndent()
            .lines().parse()

        val result = before.cycle(10).flashes

        assertThat(result).isEqualTo(204L)
    }


}

