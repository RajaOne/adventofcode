package com.raja.tmp.day13;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day13.Distress.distress;
import static org.assertj.core.api.Assertions.assertThat;

class DistressTest {

    @Test
    void getScore() {
        var input = """
                [1,1,3,1,1]
                [1,1,5,1,1]
                                
                [[1],[2,3,4]]
                [[1],4]
                                
                [9]
                [[8,7,6]]
                                
                [[4,4],4,4]
                [[4,4],4,4,4]
                                
                [7,7,7,7]
                [7,7,7]
                                
                []
                [3]
                                
                [[[]]]
                [[]]
                                
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]
                """;

        int score = distress(input).getScore();

        assertThat(score).isEqualTo(13);
    }

    @Test
    void getScore2() {
        var input = """
                [1,1,3,1,1]
                [1,1,5,1,1]
                                
                [[1],[2,3,4]]
                [[1],4]
                                
                [9]
                [[8,7,6]]
                                
                [[4,4],4,4]
                [[4,4],4,4,4]
                                
                [7,7,7,7]
                [7,7,7]
                                
                []
                [3]
                                
                [[[]]]
                [[]]
                                
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]
                """;

        int score = distress(input).getScore2();

        assertThat(score).isEqualTo(140);
    }
}
