package com.raja.tmp.day24;

import org.junit.jupiter.api.Test;

import static com.raja.tmp.day24.Blizzard.blizzard;
import static org.assertj.core.api.Assertions.assertThat;

class BlizzardTest {

	@Test
	void getScore() {
		var input = """
				#.######
				#>>.<^<#
				#.<..<<#
				#>v.><>#
				#<^v^^>#
				######.#
				""";

		int score = blizzard(input).getScore();

		assertThat(score).isEqualTo(18);
	}
}