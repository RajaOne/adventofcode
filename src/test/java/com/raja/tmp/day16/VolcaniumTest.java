package com.raja.tmp.day16;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day16.Volcanium.volcanium;
import static org.assertj.core.api.Assertions.assertThat;

class VolcaniumTest {

    @Test
    void getScore() {

        var input = """
                Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
                Valve BB has flow rate=13; tunnels lead to valves CC, AA
                Valve CC has flow rate=2; tunnels lead to valves DD, BB
                Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
                Valve EE has flow rate=3; tunnels lead to valves FF, DD
                Valve FF has flow rate=0; tunnels lead to valves EE, GG
                Valve GG has flow rate=0; tunnels lead to valves FF, HH
                Valve HH has flow rate=22; tunnel leads to valve GG
                Valve II has flow rate=0; tunnels lead to valves AA, JJ
                Valve JJ has flow rate=21; tunnel leads to valve II
                """;

        int score = volcanium(input).getScore();

        assertThat(score).isEqualTo(1651);
    }


    @Test
    void getScore2() {

        var input = """
                Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
                Valve BB has flow rate=13; tunnels lead to valves CC, AA
                Valve CC has flow rate=2; tunnels lead to valves DD, BB
                Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
                Valve EE has flow rate=3; tunnels lead to valves FF, DD
                Valve FF has flow rate=0; tunnels lead to valves EE, GG
                Valve GG has flow rate=0; tunnels lead to valves FF, HH
                Valve HH has flow rate=22; tunnel leads to valve GG
                Valve II has flow rate=0; tunnels lead to valves AA, JJ
                Valve JJ has flow rate=21; tunnel leads to valve II
                """;

        int score = volcanium(input).getScore2();

        assertThat(score).isEqualTo(1707);
    }
}
