package com.raja.tmp.day11;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day11.MonkeyMiddle.monkeyMiddle;
import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMiddleTest {

    @Test
    void should_get_score() {
        var input = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                                
                Monkey 1:
                  Starting items: 54, 65, 75, 74
                  Operation: new = old + 6
                  Test: divisible by 19
                    If true: throw to monkey 2
                    If false: throw to monkey 0
                                
                Monkey 2:
                  Starting items: 79, 60, 97
                  Operation: new = old * old
                  Test: divisible by 13
                    If true: throw to monkey 1
                    If false: throw to monkey 3
                                
                Monkey 3:
                  Starting items: 74
                  Operation: new = old + 3
                  Test: divisible by 17
                    If true: throw to monkey 0
                    If false: throw to monkey 1
                """;

        int score = monkeyMiddle(input, 4).getScore();

        assertThat(score).isEqualTo(10605);
    }

    @Test
    void should_get_score2() {
        var input = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                                
                Monkey 1:
                  Starting items: 54, 65, 75, 74
                  Operation: new = old + 6
                  Test: divisible by 19
                    If true: throw to monkey 2
                    If false: throw to monkey 0
                                
                Monkey 2:
                  Starting items: 79, 60, 97
                  Operation: new = old * old
                  Test: divisible by 13
                    If true: throw to monkey 1
                    If false: throw to monkey 3
                                
                Monkey 3:
                  Starting items: 74
                  Operation: new = old + 3
                  Test: divisible by 17
                    If true: throw to monkey 0
                    If false: throw to monkey 1
                """;

        long score = monkeyMiddle(input, 4).getScore2();

        assertThat(score).isEqualTo(2_713_310_158L);
    }


    @Test
    void tes1() {
        int n1 = (100 + 12) % 20;
        int n2 = ((100 % 20) + 12) % 20;

        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void tes11() {
        int n1 = (101 + 12) % 20;
        int n2 = ((101 % 20) + 12) % 20;

        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void tes2() {
        int n1 = (7 * 12) % 5;
        int n2 = ((7 % 5) * 12) % 5;

        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void tes22() {
        int n1 = (14 * 12) % 5;
        int n2 = ((14 % 5) * 12) % 5;

        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void tes23() {
        int n1 = (79 * 79) % 13;
        int n2 = ((79 % 13) * 79) % 13;

        assertThat(n1).isEqualTo(n2);
    }
}
