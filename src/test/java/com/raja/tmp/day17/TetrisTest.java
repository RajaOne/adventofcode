package com.raja.tmp.day17;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day17.Tetris.tetris;
import static org.assertj.core.api.Assertions.assertThat;

class TetrisTest {

    @Test
    void getScore() {
        var input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;

        long score = tetris(input).getScore2022();

        assertThat(score).isEqualTo(3068);
    }

    @Test
    @Disabled("takes too long")
    void getScore2() {
        var input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;

        long score = tetris(input).getScoreTrillion();

        assertThat(score).isEqualTo(1_514_285_714_288L);
    }
}
