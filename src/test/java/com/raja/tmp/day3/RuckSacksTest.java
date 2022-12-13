package com.raja.tmp.day3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raja.tmp.day3.RuckSacks.ruckSacks;
import static com.raja.tmp.day3.RuckSacks.ruckSacksWithGroups;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RuckSacksTest {

    @Test
    void should_get_one() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                """;
        int sum = ruckSacks(input).getSumPrio();

        assertThat(sum).isEqualTo(16);
    }

    @Test
    void should_get_two() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                """;
        int sum = ruckSacks(input).getSumPrio();

        assertThat(sum).isEqualTo(16 + 38);
    }

    @Test
    void should_get_sum() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        int sum = ruckSacks(input).getSumPrio();

        assertThat(sum).isEqualTo(157);
    }

    @Test
    void should_get_group1() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                """;
        int sum = ruckSacksWithGroups(input).getSumPrio();

        assertThat(sum).isEqualTo(18);
    }

    @Test
    void should_get_groups() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        int sum = ruckSacksWithGroups(input).getSumPrio();

        assertThat(sum).isEqualTo(70);
    }
}
