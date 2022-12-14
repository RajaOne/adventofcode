package com.raja.tmp.day14;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day14.Regolith.regolith;
import static com.raja.tmp.day14.Regolith.regolith2;
import static org.assertj.core.api.Assertions.assertThat;


class RegolithTest {

    @Test
    void getScore() {
        var input = """
                498,4 -> 498,6 -> 496,6
                503,4 -> 502,4 -> 502,9 -> 494,9
                """;

        int score = regolith(input, 600).getScore();

        assertThat(score).isEqualTo(24);
    }

    @Test
    void getScore2() {
        var input = """
                498,4 -> 498,6 -> 496,6
                503,4 -> 502,4 -> 502,9 -> 494,9
                """;

        int score = regolith2(input, 600).getScore();

        assertThat(score).isEqualTo(93);
    }
}
