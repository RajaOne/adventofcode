package com.raja.tmp.day20;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day20.Encryption.encryption;
import static org.assertj.core.api.Assertions.assertThat;

class EncryptionTest {

    @Test
    void getScore() {
        var input = """
                1
                2
                -3
                3
                -2
                0
                4
                """;

        int score = encryption(input).getScore();

        assertThat(score).isEqualTo(3);
    }

    @Test
    void getScore2() {
        var input = """
                1
                2
                -3
                3
                -2
                0
                4
                """;

        long score = encryption(input).getScore2();

        assertThat(score).isEqualTo(1623178306);
    }
}
