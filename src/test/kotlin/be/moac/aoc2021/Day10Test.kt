package be.moac.aoc2021

import be.moac.aoc2021.Day10.isCorrupt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {

    @Test
    fun `part one`() {

        val input = listOf(
            "[({(<(())[]>[[{[]{<()<>>".toCharArray(),
            "[(()[<>])]({[<{<<[]>>(".toCharArray(),
            "{([(<{}[<>[]}>{[]{[(<()>".toCharArray(),
            "(((({<>}<{<{<>}{[]{[]{}".toCharArray(),
            "[[<[([]))<([[{}[[()]]]".toCharArray(),
            "[{[{({}]{}}([{[{{{}}([]".toCharArray(),
            "{<[[]]>}<{[{[{[]{()[[[]".toCharArray(),
            "[<(<(<(<{}))><([]([]()".toCharArray(),
            "<{([([[(<>()){}]>(<<{{".toCharArray(),
            "<{([{{}}[<[[[<>{}]]]>[]]".toCharArray(),
        )

        val result = Day10 partOne input

        assertThat(result).isEqualTo(26397)
    }

    @Test
    fun `part two`() {

        val input = listOf<CharArray>()

        val result = Day10 partTwo input

        assertThat(result).isEqualTo(26397)
    }


    @Test
    fun `line is corrupt`() {
        
        assertThat("{([(<{}[<>[]}>{[]{[(<()>".toCharArray().isCorrupt()).isEqualTo('}')
        assertThat("[[<[([]))<([[{}[[()]]]".toCharArray().isCorrupt()).isEqualTo(')')

        assertThat("[{[{({}]{}}([{[{{{}}([]".toCharArray().isCorrupt()).isEqualTo(']')
        assertThat("[<(<(<(<{}))><([]([]()".toCharArray().isCorrupt()).isEqualTo(')')
        assertThat("<{([([[(<>()){}]>(<<{{".toCharArray().isCorrupt()).isEqualTo('>')

        assertThat("[({(<(())[]>[[{[]{<()<>>".toCharArray().isCorrupt()).isNull()

    }
}

