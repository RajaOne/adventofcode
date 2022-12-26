package com.raja.tmp.day21;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day21.MonkeyMath.monkeyMath;
import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMathTest {

    @Test
    void getScore() {
        var input = """
                root: pppw + sjmn
                dbpl: 5
                cczh: sllz + lgvd
                zczc: 2
                ptdq: humn - dvpt
                dvpt: 3
                lfqf: 4
                humn: 5
                ljgn: 2
                sjmn: drzm * dbpl
                sllz: 4
                pppw: cczh / lfqf
                lgvd: ljgn * ptdq
                drzm: hmdt - zczc
                hmdt: 32
                """;

        long score = monkeyMath(input).getScore();

        assertThat(score).isEqualTo(152);
    }

    @Test
    void getScore2() {
        var input = """
                root: pppw + sjmn
                dbpl: 5
                cczh: sllz + lgvd
                zczc: 2
                ptdq: humn - dvpt
                dvpt: 3
                lfqf: 4
                humn: 301
                ljgn: 2
                sjmn: drzm * dbpl
                sllz: 4
                pppw: cczh / lfqf
                lgvd: ljgn * ptdq
                drzm: hmdt - zczc
                hmdt: 32
                """;

        long score = monkeyMath(input).getScore();

        assertThat(score).isEqualTo(301);
    }
}
