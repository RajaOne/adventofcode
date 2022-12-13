package com.raja.tmp.day12;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day12.HillClimb.hillClimb;
import static org.assertj.core.api.Assertions.assertThat;

class HillClimbTest {

    @Test
    void getScore() {
        var input = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi
                """;

        int score = hillClimb(input).getScore();

        assertThat(score).isEqualTo(31);
    }


    @Test
    void getScore2() {
        var input = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi
                """;

        int score = HillClimb.hillClimbAnyA(input).getScore();

        assertThat(score).isEqualTo(29);
    }
}
