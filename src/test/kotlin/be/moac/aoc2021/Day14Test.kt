package be.moac.aoc2021

import be.moac.aoc2021.Day14.rules
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day14Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C",
        )
        val result = Day14 partOne input

        assertThat(result).isEqualTo(1588L)
    }

    @Test
    fun `parse rules`() {
        assertThat(listOf("NNCB", "", "CH -> B", "HH -> N", "CB -> H", "NH -> C").rules())
            .containsEntry('C' to 'H', 'B')
            .containsEntry('H' to 'H', 'N')
            .containsEntry('C' to 'B', 'H')
            .containsEntry('N' to 'H', 'C')
    }

    @Test
    fun `part two`() {

        val input = listOf(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C",
        )

        val result = Day14 partTwo input

        assertThat(result).isEqualTo(2188189693529L)
    }


}

