package com.raja.tmp.day22;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day22.MonkeyMap.monkeyMap;
import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMapTest {

    @Test
    void getScore() {
        var input = """
                        ...#
                        .#..
                        #...
                        ....
                ...#.......#
                ........#...
                ..#....#....
                ..........#.
                        ...#....
                        .....#..
                        .#......
                        ......#.
                                
                10R5L5R10L4R5L5
                """;

        int score = monkeyMap(input).getScore();

        assertThat(score).isEqualTo(6032);
    }
}
