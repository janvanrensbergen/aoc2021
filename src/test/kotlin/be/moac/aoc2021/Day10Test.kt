package be.moac.aoc2021

import be.moac.aoc2021.Day10.calculatePoints
import be.moac.aoc2021.Day10.complete
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

        val result = Day10 partTwo input

        assertThat(result).isEqualTo(288957)
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

    @Test
    fun `complete line`() {
        assertThat("[({(<(())[]>[[{[]{<()<>>".toCharArray().complete()).isEqualTo("}}]])})]")
        assertThat("[(()[<>])]({[<{<<[]>>(".toCharArray().complete()).isEqualTo(")}>]})")
        assertThat("(((({<>}<{<{<>}{[]{[]{}".toCharArray().complete()).isEqualTo("}}>}>))))")
        assertThat("{<[[]]>}<{[{[{[]{()[[[]".toCharArray().complete()).isEqualTo("]]}}]}]}>")
        assertThat("<{([{{}}[<[[[<>{}]]]>[]]".toCharArray().complete()).isEqualTo("])}>")
    }

    @Test
    fun `calculate total points`() {
        assertThat("}}]])})]".calculatePoints()).isEqualTo(288957)
        assertThat(")}>]})".calculatePoints()).isEqualTo(5566)
        assertThat("}}>}>))))".calculatePoints()).isEqualTo(1480781)
        assertThat("]]}}]}]}>".calculatePoints()).isEqualTo(995444)
        assertThat("])}>".calculatePoints()).isEqualTo(294)
    }
}

