package com.raja.tmp.day8;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day8.ForrestGrid.forrestGrid;
import static org.assertj.core.api.Assertions.assertThat;

class ForrestGridTest {

    @Test
    void should_count_visible() {
        var input = """
                30373
                25512
                65332
                33549
                35390
                """;

        int amount = forrestGrid(input, 5).getVisible();

        assertThat(amount).isEqualTo(21);
    }


    @Test
    void should_get_highest_score() {
        var input = """
                30373
                25512
                65332
                33549
                35390
                """;

        int amount = forrestGrid(input, 5).getHighestScore();

        assertThat(amount).isEqualTo(8);
    }
}
