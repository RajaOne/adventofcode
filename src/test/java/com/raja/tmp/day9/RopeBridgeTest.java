package com.raja.tmp.day9;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day9.RopeBridge.ropeBridge;
import static com.raja.tmp.day9.RopeBridge.ropeBridge9;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

class RopeBridgeTest {

    @Test
    void should_get_count() {
        var input = """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2
                """;

        int count = ropeBridge(input).getCount();

        assertThat(count).isEqualTo(13);
    }

    @Test
    void should_get_9() {
        var input = """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2
                """;

        int count = ropeBridge9(input).getCount();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void should_get_9_2() {
        var input = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
                """;

        int count = ropeBridge9(input).getCount();

        assertThat(count).isEqualTo(36);
    }
}
