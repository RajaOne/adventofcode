package com.raja.tmp.day4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day4.DetectSection.detectOverlap;
import static com.raja.tmp.day4.DetectSection.detectSection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DetectSectionTest {

    @Test
    void should_count_one() {
        var input = """
                5-7,7-9
                2-8,3-7
                """;

        int count = detectSection(input).count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void should_count_two() {
        var input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;

        int count = detectSection(input).count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void should_count_overlap_one() {
        var input = """
                5-7,7-9
                2-8,3-7
                """;

        int count = detectOverlap(input).count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void should_count_overlap_two() {
        var input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;

        int count = detectOverlap(input).count();

        assertThat(count).isEqualTo(4);
    }
}
