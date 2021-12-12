package be.moac.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "dc-end",
            "HN-start",
            "start-kj",
            "dc-start",
            "dc-HN",
            "LN-dc",
            "HN-end",
            "kj-sa",
            "kj-HN",
            "kj-dc",
        )

        val result = Day12 partOne input

        assertThat(result).isEqualTo(19L)
    }

    @Test
    fun `part one simple example`() {

        val input = listOf(
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end",
        )

        val result = Day12 partOne input

        assertThat(result).isEqualTo(10L)
    }


    @Test
    fun `part one larger example`() {

        val input = listOf(
            "fs-end",
            "he-DX",
            "fs-he",
            "start-DX",
            "pj-DX",
            "end-zg",
            "zg-sl",
            "zg-pj",
            "pj-he",
            "RW-he",
            "fs-DX",
            "pj-RW",
            "zg-RW",
            "start-pj",
            "he-WI",
            "zg-he",
            "pj-fs",
            "start-RW",
        )

        val result = Day12 partOne input

        assertThat(result).isEqualTo(226L)
    }

    @Test
    fun `part two`() {

        val input = listOf(
            "dc-end",
            "HN-start",
            "start-kj",
            "dc-start",
            "dc-HN",
            "LN-dc",
            "HN-end",
            "kj-sa",
            "kj-HN",
            "kj-dc",
        )

        val result = Day12 partTwo input

        assertThat(result).isEqualTo(103L)
    }



    @Test
    fun `part two simple example`() {

        val input = listOf(
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end",
        )

        val result = Day12 partTwo input

        assertThat(result).isEqualTo(36L)
    }


    @Test
    fun `part two larger example`() {

        val input = listOf(
            "fs-end",
            "he-DX",
            "fs-he",
            "start-DX",
            "pj-DX",
            "end-zg",
            "zg-sl",
            "zg-pj",
            "pj-he",
            "RW-he",
            "fs-DX",
            "pj-RW",
            "zg-RW",
            "start-pj",
            "he-WI",
            "zg-he",
            "pj-fs",
            "start-RW",
        )

        val result = Day12 partTwo input

        assertThat(result).isEqualTo(3509L)
    }

}

