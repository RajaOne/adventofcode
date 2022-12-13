package com.raja.tmp;

import com.raja.tmp.day2.RockPaperScissors;
import com.raja.tmp.day5.MoveStack;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Path;

import static com.raja.tmp.day1.CaloricCounter.caloricCounter;
import static com.raja.tmp.day10.CathodeRay.cathodeRay;
import static com.raja.tmp.day11.MonkeyMiddle.monkeyMiddle;
import static com.raja.tmp.day12.HillClimb.hillClimb;
import static com.raja.tmp.day12.HillClimb.hillClimbAnyA;
import static com.raja.tmp.day13.Distress.distress;
import static com.raja.tmp.day2.RockPaperScissors.rockPaperScissors;
import static com.raja.tmp.day3.RuckSacks.ruckSacks;
import static com.raja.tmp.day3.RuckSacks.ruckSacksWithGroups;
import static com.raja.tmp.day4.DetectSection.detectOverlap;
import static com.raja.tmp.day4.DetectSection.detectSection;
import static com.raja.tmp.day6.DetectMarker.detectMarker;
import static com.raja.tmp.day7.FileSize.fileSize;
import static com.raja.tmp.day8.ForrestGrid.forrestGrid;
import static com.raja.tmp.day9.RopeBridge.ropeBridge;
import static com.raja.tmp.day9.RopeBridge.ropeBridge9;
import static java.nio.file.Files.readString;

@SpringBootApplication
public class TmpApplication {

	public static void main(String[] args) throws IOException {
		var input = readString(Path.of("day1.txt"));
		int count = caloricCounter(input).getMax();
		System.out.println("Day 1 part 1: total count (should be 71506): " + count);

		count = caloricCounter(input).getTopThree();
		System.out.println("Day 1 part 2: total count (should be 209603): " + count);

		input = readString(Path.of("day2.txt"));
		int score = rockPaperScissors(input).getScore();
		System.out.println("Day 2 part 1: RPS score(should be 15632): " + score);

		score = RockPaperScissors.rockPaperScissorsWithEndResult(input).getScore();
		System.out.println("Day 2 part 2: RPS score rockPaperScissorsWithEndResult(): " + score);

		input = readString(Path.of("day3.txt"));
		score = ruckSacks(input).getSumPrio();
		System.out.println("Day 3 part 1: Rocksack sum (should be 8039): " + score);

		score = ruckSacksWithGroups(input).getSumPrio();
		System.out.println("Day 3 part 2: Rocksack with groups sum (should be 2510): " + score);

		input = readString(Path.of("day4.txt"));
		score = detectSection(input).count();
		System.out.println("Day 4 part 1: Detect section count (should be 448): " + score);

		score = detectOverlap(input).count();
		System.out.println("Day 4 part 2: Detect overlap count (should be 794): " + score);

		input = readString(Path.of("day5.txt"));
		var message = MoveStack.moveStack(input, 9).getCratesOnTop();
		System.out.println("Day 5 part 1: move stack message (should be TLNGFGMFN): " + message);

		message = MoveStack.moveStack(input, 9).get9001CratesOnTop();
		System.out.println("Day 5 part 2: move stack message (should be FGLQJCMBD): " + message);

		input = readString(Path.of("day6.txt"));
		score = detectMarker(input).firstMarker();
		System.out.println("Day 6 part 1: Detect marker (should be 1566): " + score);

		score = detectMarker(input).firstMessageMarker();
		System.out.println("Day 6 part 2: Detect marker (should be 2265): " + score);

		input = readString(Path.of("day7.txt"));
		score = fileSize(input).getSizeOfTops();
		System.out.println("Day 7 part 1: Detect size (should be 919137): " + score);

		score = fileSize(input).getSmallestDeletable();
		System.out.println("Day 7 part 2: Delete (should be 2877389): " + score);

		input = readString(Path.of("day8.txt"));
		score = forrestGrid(input, 99).getVisible();
		System.out.println("Day 8 part 1: Visible (should be 1708): " + score);

		score = forrestGrid(input, 99).getHighestScore();
		System.out.println("Day 8 part 2: Visible score (should be 504000): " + score);

		input = readString(Path.of("day9.txt"));
		score = ropeBridge(input).getCount();
		System.out.println("Day 9 part 1: Count (should be 6563): " + score);

		score = ropeBridge9(input).getCount();
		System.out.println("Day 9 part 2: Count (should be 2653): " + score);

		input = readString(Path.of("day10.txt"));
		score = cathodeRay(input).getScore();
		System.out.println("Day 10 part 1: Strength (should be 15360): " + score);
		System.out.println("Day 10 part 2: Strength (should be PHLHJGZA)");

		input = readString(Path.of("day11.txt"));
		score = monkeyMiddle(input, 8).getScore();
		System.out.println("Day 11 part 1: Count (should be 61005): " + score);

		long scorelong = monkeyMiddle(input, 8).getScore2();
		System.out.println("Day 11 part 2: Count (should be not 20567144694): " + scorelong);

		input = readString(Path.of("day12.txt"));
		score = hillClimb(input).getScore();
		System.out.println("Day 12 part 1: Score (should be 534): " + score);

		score = hillClimbAnyA(input).getScore();
		System.out.println("Day 12 part 2: Score (should be 525): " + score);

		input = readString(Path.of("day13.txt"));
		score = distress(input).getScore();
		System.out.println("Day 13 part 1: Score (should be 6369): " + score);

		score = distress(input).getScore2();
		System.out.println("Day 13 part 2: Score (should be 25800): " + score);
	}

}
