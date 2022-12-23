package com.raja.tmp.day18;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day18.Boulders.boulders;
import static org.assertj.core.api.Assertions.assertThat;

class BouldersTest {

    @Test
    void getScore() {
        var input = """
                2,2,2
                1,2,2
                3,2,2
                2,1,2
                2,3,2
                2,2,1
                2,2,3
                2,2,4
                2,2,6
                1,2,5
                3,2,5
                2,1,5
                2,3,5
                """;

        int score = boulders(input).getScore();

        assertThat(score).isEqualTo(64);
    }

    @Test
    void getScore2() {
        var input = """
                2,2,2
                1,2,2
                3,2,2
                2,1,2
                2,3,2
                2,2,1
                2,2,3
                2,2,4
                2,2,6
                1,2,5
                3,2,5
                2,1,5
                2,3,5
                """;

        int score = boulders(input).getScore2();

        assertThat(score).isEqualTo(58);
    }
}
