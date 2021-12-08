package be.moac.aoc2021

import be.moac.aoc2021.Day08.Digit.*
import be.moac.aoc2021.Day08.toDigit
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day08Test {
    @Test
    internal fun `part 1`() {
        val input = listOf(
            "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
        )

        val result = Day08 partOne input

        assertThat(result).isEqualTo(26)
    }

    @Test
    internal fun `part 2`() {
        val input = listOf(
            "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
        )

        val result = Day08 partTwo input

        assertThat(result).isEqualTo(61229)
    }



    @Test
    internal fun `parse digit`() {
        assertThat(listOf("cf","acf","bcdf","acdeg","abdfg","acdfg","abcefg","abdefg","abcdfg","abcdefg").toDigit())
            .containsEntry("cf", 1)
            .containsEntry("bcdf", 4)
            .containsEntry("acf", 7)
            .containsEntry("abcdefg", 8)
            .containsEntry("abcdfg", 9)
            .containsEntry("acdfg", 3)
            .containsEntry("abcefg", 0)
            .containsEntry("abdefg", 6)
            .containsEntry("abdfg", 5)
            .containsEntry("acdeg", 2)


        assertThat(listOf("acedgfb","cdfbe","gcdfa","fbcad","dab","cefabd","cdfgeb","eafb","cagedb","ab").toDigit())
            .containsEntry("ab", 1)
            .containsEntry("abef", 4)
            .containsEntry("abd", 7)
            .containsEntry("abcdefg", 8)
            .containsEntry("bcdef", 5)
            .containsEntry("acdfg", 2)
            .containsEntry("abcdf", 3)
            .containsEntry("abcdef", 9)
            .containsEntry("bcdefg", 6)
            .containsEntry("abcdeg", 0)
    }
}
