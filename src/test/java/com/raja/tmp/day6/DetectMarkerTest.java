package com.raja.tmp.day6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day6.DetectMarker.detectMarker;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DetectMarkerTest {

    @Test
    void should_find_marker() {
        var input = """
                mjqjpqmgbljsphdztnvjfqwrcgsmlb
                """;
        int marker = detectMarker(input).firstMarker();

        assertThat(marker).isEqualTo(7);
    }

    @Test
    void should_find_marker1() {
        var input = """
                bvwbjplbgvbhsrlpgdmjqwftvncz
                """;
        int marker = detectMarker(input).firstMarker();

        assertThat(marker).isEqualTo(5);
    }

    @Test
    void should_find_marker2() {
        var input = """
                nppdvjthqldpwncqszvftbrmjlhg
                """;
        int marker = detectMarker(input).firstMarker();

        assertThat(marker).isEqualTo(6);
    }

    @Test
    void should_find_marker3() {
        var input = """
                nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
                """;
        int marker = detectMarker(input).firstMarker();

        assertThat(marker).isEqualTo(10);
    }

    @Test
    void should_find_marker4() {
        var input = """
                zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
                """;
        int marker = detectMarker(input).firstMarker();

        assertThat(marker).isEqualTo(11);
    }

    @Test
    void should_find_message_marker() {
        var input = """
                mjqjpqmgbljsphdztnvjfqwrcgsmlb
                """;
        int marker = detectMarker(input).firstMessageMarker();

        assertThat(marker).isEqualTo(19);
    }

    @Test
    void should_find_message_marker1() {
        var input = """
                bvwbjplbgvbhsrlpgdmjqwftvncz
                """;
        int marker = detectMarker(input).firstMessageMarker();

        assertThat(marker).isEqualTo(23);
    }

    @Test
    void should_find_message_marker2() {
        var input = """
                nppdvjthqldpwncqszvftbrmjlhg
                """;
        int marker = detectMarker(input).firstMessageMarker();

        assertThat(marker).isEqualTo(23);
    }

    @Test
    void should_find_message_marker3() {
        var input = """
                nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
                """;
        int marker = detectMarker(input).firstMessageMarker();

        assertThat(marker).isEqualTo(29);
    }

    @Test
    void should_find_message_marker4() {
        var input = """
                zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
                """;
        int marker = detectMarker(input).firstMessageMarker();

        assertThat(marker).isEqualTo(26);
    }
}
