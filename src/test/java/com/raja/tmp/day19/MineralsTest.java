package com.raja.tmp.day19;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day19.Minerals.minerals;
import static org.assertj.core.api.Assertions.assertThat;

class MineralsTest {

    @Test
    void getScore() {
        var input = """
                Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
                """;

        int score = minerals(input).getScore();

        assertThat(score).isEqualTo(9);
    }

    @Test
    void getScoreWithTwo() {
        var input = """
                Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
                Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
                """;

        int score = minerals(input).getScore();

        assertThat(score).isEqualTo(33);
    }

    @Test
    void getScore2() {
        var input = """
                Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
                """;

        int score = minerals(input).getScore2();

        assertThat(score).isEqualTo(56);
    }

    @Test
    void getScore2WithTwo() {
        var input = """
                Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
                """;

        int score = minerals(input).getScore2();

        assertThat(score).isEqualTo(62);
    }

    @Test
    void getScore2Real1() {
        var input = """
                Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 14 clay. Each geode robot costs 2 ore and 16 obsidian.
                """;

        int score = minerals(input).getScore2();

        assertThat(score).isEqualTo(11);
    }

    @Test
    void getScore2Real2() {
        var input = """
                Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 2 ore. Each obsidian robot costs 2 ore and 15 clay. Each geode robot costs 2 ore and 7 obsidian.
                """;

        int score = minerals(input).getScore2();

        assertThat(score).isEqualTo(79);
    }

    @Test
    void getScore2Real3() {
        var input = """
                Blueprint 3: Each ore robot costs 4 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
                """;

        int score = minerals(input).getScore2();

        assertThat(score).isEqualTo(43);
    }

}
