package com.raja.tmp.day5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day5.MoveStack.moveStack;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoveStackTest {

    @Test
    void should_get_on_top_1() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                """;

        String message = moveStack(input, 3).getCratesOnTop();

        assertThat(message).isEqualTo("DCP");
    }

    @Test
    void should_get_on_top_2() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                """;

        String message = moveStack(input, 3).getCratesOnTop();

        assertThat(message).isEqualTo(" CZ");
    }

    @Test
    void should_get_on_top() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;

        String message = moveStack(input, 3).getCratesOnTop();

        assertThat(message).isEqualTo("CMZ");
    }

    @Test
    void should_get_on_top_1_with_9001() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                """;

        String message = moveStack(input, 3).get9001CratesOnTop();

        assertThat(message).isEqualTo("DCP");
    }

    @Test
    void should_get_on_top_2_with_9001() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                """;

        String message = moveStack(input, 3).get9001CratesOnTop();

        assertThat(message).isEqualTo(" CD");
    }

    @Test
    void should_get_on_top_with_9001() {
        var input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;

        String message = moveStack(input, 3).get9001CratesOnTop();

        assertThat(message).isEqualTo("MCD");
    }
}
