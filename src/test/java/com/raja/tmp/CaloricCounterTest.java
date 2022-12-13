package com.raja.tmp;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day1.CaloricCounter.caloricCounter;
import static org.assertj.core.api.Assertions.assertThat;

class CaloricCounterTest {

    @Test
    void should_count_one() {
        var input = """
                1000
                """;
        int count = caloricCounter(input).getMax();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void should_count_one_more() {
        var input = """
                1000
                2000
                """;
        int count = caloricCounter(input).getMax();

        assertThat(count).isEqualTo(3000);
    }

    @Test
    void should_count_two() {
        var input = """
                1000
                2000
                                
                4000
                """;
        int count = caloricCounter(input).getMax();

        assertThat(count).isEqualTo(4000);
    }

    @Test
    void should_count_test() {
        var input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;
        int count = caloricCounter(input).getMax();

        assertThat(count).isEqualTo(24000);
    }

    @Test
    void should_count_top_three_when_one() {
        var input = """
                1000
                """;
        int count = caloricCounter(input).getTopThree();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void should_count_top_three_when_four() {
        var input = """
                1000
                2000
                
                3000
                
                4000
                5000
                
                6000
                """;
        int count = caloricCounter(input).getTopThree();

        assertThat(count).isEqualTo(6000 + 9000 + 3000);
    }

    @Test
    void should_count_top_three() {
        var input = """
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000
                """;
        int count = caloricCounter(input).getTopThree();

        assertThat(count).isEqualTo(45000);
    }
}
