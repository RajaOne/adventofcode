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

    @Test
    void getScore2() {
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

        MonkeyMap monkeyMap = monkeyMap(input);
        monkeyMap.makeTestCubeMap();
        int score = monkeyMap.getScore2();

        assertThat(score).isEqualTo(5031);
    }
}
