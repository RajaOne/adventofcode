package com.raja.tmp.day2;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day2.RockPaperScissors.rockPaperScissors;
import static org.assertj.core.api.Assertions.assertThat;

class RockPaperScissorsTest {

    @Test
    void should_get_score_one() {
        var input = """
                A Y
                """;

        int score = rockPaperScissors(input).getScore();

        assertThat(score).isEqualTo(8);
    }

    @Test
    void should_get_score_two() {
        var input = """
                A Y
                B X
                """;

        int score = rockPaperScissors(input).getScore();

        assertThat(score).isEqualTo(9);
    }

    @Test
    void should_get_score() {
        var input = """
                A Y
                B X
                C Z
                """;

        int score = rockPaperScissors(input).getScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void should_get_score_one_with_expected_end_result() {
        var input = """
                A Y
                """;

        int score = RockPaperScissors.rockPaperScissorsWithEndResult(input).getScore();

        assertThat(score).isEqualTo(4);
    }

    @Test
    void should_get_score_two_with_expected_end_result() {
        var input = """
                A Y
                B X
                """;

        int score = RockPaperScissors.rockPaperScissorsWithEndResult(input).getScore();

        assertThat(score).isEqualTo(5);
    }

    @Test
    void should_get_score_with_expected_end_result() {
        var input = """
                A Y
                B X
                C Z
                """;

        int score = RockPaperScissors.rockPaperScissorsWithEndResult(input).getScore();

        assertThat(score).isEqualTo(12);
    }
}
