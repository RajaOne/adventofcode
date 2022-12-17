package com.raja.tmp.day15;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day15.BeaconExclusion.beaconExclusion;
import static com.raja.tmp.day15.BeaconExclusion.beaconExclusion2;
import static org.assertj.core.api.Assertions.assertThat;

class BeaconExclusionTest {

    @Test
    void getScore() {
        var input = """
                Sensor at x=2, y=18: closest beacon is at x=-2, y=15
                Sensor at x=9, y=16: closest beacon is at x=10, y=16
                Sensor at x=13, y=2: closest beacon is at x=15, y=3
                Sensor at x=12, y=14: closest beacon is at x=10, y=16
                Sensor at x=10, y=20: closest beacon is at x=10, y=16
                Sensor at x=14, y=17: closest beacon is at x=10, y=16
                Sensor at x=8, y=7: closest beacon is at x=2, y=10
                Sensor at x=2, y=0: closest beacon is at x=2, y=10
                Sensor at x=0, y=11: closest beacon is at x=2, y=10
                Sensor at x=20, y=14: closest beacon is at x=25, y=17
                Sensor at x=17, y=20: closest beacon is at x=21, y=22
                Sensor at x=16, y=7: closest beacon is at x=15, y=3
                Sensor at x=14, y=3: closest beacon is at x=15, y=3
                Sensor at x=20, y=1: closest beacon is at x=15, y=3
                """;

        int score = beaconExclusion(input, 10).getScore();

        assertThat(score).isEqualTo(26);
    }

    @Test
    void getScore2() {
        var input = """
                Sensor at x=2, y=18: closest beacon is at x=-2, y=15
                Sensor at x=9, y=16: closest beacon is at x=10, y=16
                Sensor at x=13, y=2: closest beacon is at x=15, y=3
                Sensor at x=12, y=14: closest beacon is at x=10, y=16
                Sensor at x=10, y=20: closest beacon is at x=10, y=16
                Sensor at x=14, y=17: closest beacon is at x=10, y=16
                Sensor at x=8, y=7: closest beacon is at x=2, y=10
                Sensor at x=2, y=0: closest beacon is at x=2, y=10
                Sensor at x=0, y=11: closest beacon is at x=2, y=10
                Sensor at x=20, y=14: closest beacon is at x=25, y=17
                Sensor at x=17, y=20: closest beacon is at x=21, y=22
                Sensor at x=16, y=7: closest beacon is at x=15, y=3
                Sensor at x=14, y=3: closest beacon is at x=15, y=3
                Sensor at x=20, y=1: closest beacon is at x=15, y=3
                """;

        long score = beaconExclusion2(input, 20).getScore2();

        assertThat(score).isEqualTo(56000011);


        var test = """
                   ..........
                   ....#.....
                   ...###.... <- y
                   ..#####...
                   .#######..
                   ..#####...
                   ...###....
                   ....#.....
                   """;

//        y = 2
//        int beginX = sensX - (y + size - sensY)
//        int beginX = 4 - (2 + 3 - 4);
//        int beginX = sensX - (range - abs(y - senY))
        int beginX = 4 - (3 - 2);
//        int beginX = 3
//        int endX = sensX + (y + size - sensY)
//        int endX = 4 + (2 + 3 - 4);
//        int endX = sensX + (range - abs(y - senY))
        int endX = 4 + (3 - 2);
//        int endX = 5

//        y = 0
//        int beginX = 4 - (0 + 3 - 4);
//        int beginX = 5
//        int endX = 4 + (0 + 3 - 4);
//        int endX = 3

//        y = 1
//        int beginX = 4 - (1 + 3 - 4);
//        int beginX = 4
//        int endX = 4 + (1 + 3 - 4);
//        int endX = 4

//        y = 4
//        int beginX = 4 - (4 + 3 - 4);
//        int beginX = 1
//        int endX = 4 + (4 + 3 - 4);
//        int endX = 7

//        y = 5
//        int beginX = sensX - (y + range - sensY)
//        int beginX = sensX - (range - abs(y - senY))
//        int beginX = 4 - (3 - (abs(5 - 4))
//        int beginX = 4 - 2
//        int beginX = 2

//        int endX = sensX + (range - abs(y - senY))
//        int endX = 4 + (3 - abs(5 - 4))
//        int endX = 4 + (2)
//        int endX = 6
    }
}
