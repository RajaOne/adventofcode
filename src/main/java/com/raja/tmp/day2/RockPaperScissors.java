package com.raja.tmp.day2;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RockPaperScissors {

    List<Integer> scores;

    private RockPaperScissors() {
        scores = new ArrayList<>();
    }

    public static RockPaperScissors rockPaperScissors(String input) {
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        String[] matches = input.split("\n");

        for (String match : matches) {
            String[] hands = match.split(" ");
            Hand playerOneHand = Hand.from(hands[0]);
            Hand playerTwoHand = Hand.from(hands[1]);
            int matchScore = playerOneHand.scoreFrom(playerTwoHand);
            int score = playerTwoHand.score();
            rockPaperScissors.getScores().add(matchScore + score);
        }
        return rockPaperScissors;
    }

    public static RockPaperScissors rockPaperScissorsWithEndResult(String input) {
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        String[] matches = input.split("\n");

        for (String match : matches) {
            String[] matchValues = match.split(" ");
            Hand playerOneHand = Hand.from(matchValues[0]);
            Hand playerTwoHand = Hand.from(playerOneHand, matchValues[1]);
            int matchScore = playerOneHand.scoreFrom(playerTwoHand);
            int score = playerTwoHand.score();
            rockPaperScissors.getScores().add(matchScore + score);
        }
        return rockPaperScissors;
    }

    public int getScore() {
        return scores.stream()
                .reduce(0, Integer::sum);
    }

}
