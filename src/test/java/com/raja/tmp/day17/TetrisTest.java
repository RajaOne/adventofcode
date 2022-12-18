package com.raja.tmp.day17;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day17.Tetris.tetris;
import static org.assertj.core.api.Assertions.assertThat;

class TetrisTest {

    @Test
    void getScore() {
        var input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;

        int score = tetris(input).getScore();

        assertThat(score).isEqualTo(3068);
    }

    @Test
    void getScore2() {
        var input = """
                >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
                """;

        long score = tetris(input).getScore2();

        assertThat(score).isEqualTo(1514285714288L);
    }
}
