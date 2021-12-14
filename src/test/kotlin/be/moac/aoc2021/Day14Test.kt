package be.moac.aoc2021

import be.moac.aoc2021.Day14.apply
import be.moac.aoc2021.Day14.polymerTemplate
import be.moac.aoc2021.Day14.rules
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
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
    fun `parse polymer template`() {
        assertThat(listOf("NNCB", "", "CH -> B", "HH -> N", "CB -> H", "NH -> C").polymerTemplate()).isEqualTo("NNCB")
    }

    @Test
    fun `parse rules`() {
        assertThat(listOf("NNCB", "", "CH -> B", "HH -> N", "CB -> H", "NH -> C").rules())
            .containsEntry("CH", "B")
            .containsEntry("HH", "N")
            .containsEntry("CB", "H")
            .containsEntry("NH", "C")
    }

    @Test
    fun `apply rules`() {
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

        val rules = input.rules()

        assertThat("NNCB".apply(rules)).isEqualTo("NCNBCHB")
        assertThat("NCNBCHB".apply(rules)).isEqualTo("NBCCNBBBCBHCB")
        assertThat("NBCCNBBBCBHCB".apply(rules)).isEqualTo("NBBBCNCCNBBNBNBBCHBHHBCHB")
        assertThat("NBBBCNCCNBBNBNBBCHBHHBCHB".apply(rules)).isEqualTo("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB")

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

        assertThat(result).isEqualTo(0L)
    }

}

