package com.raja.tmp.day3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class RuckSacks {

    List<Character> characters;

    public RuckSacks() {
        characters = new ArrayList<>();
    }

    public static RuckSacks ruckSacks(String input) {
        RuckSacks ruckSacks = new RuckSacks();
        String[] ruckSacksStrings = input.split("\n");
        for (String ruckSack : ruckSacksStrings) {
            String partOne = ruckSack.substring(0, ruckSack.length() / 2);
            String partTwo = ruckSack.substring(ruckSack.length() / 2);
            partOne.chars()
                    .filter(value -> partTwo.chars().anyMatch(value1 -> value == value1))
                    .findFirst()
                    .ifPresent(value -> ruckSacks.getCharacters().add((char)value));
        }
        return ruckSacks;
    }

    public static RuckSacks ruckSacksWithGroups(String input) {
        RuckSacks ruckSacks = new RuckSacks();
        String[] ruckSacksStrings = input.split("\n");
        for (int i = 0; i < ruckSacksStrings.length/3; i++) {
            String elfItems = ruckSacksStrings[i*3];
            String elfItems2 = ruckSacksStrings[i*3 + 1];
            String elfItems3 = ruckSacksStrings[i*3 + 2];
            int commonItem = elfItems.chars()
                    .filter(value -> elfItems2.chars().anyMatch(value1 -> value1 == value))
                    .filter(value -> elfItems3.chars().anyMatch(value1 -> value1 == value))
                    .findFirst()
                    .orElseThrow();
            ruckSacks.getCharacters().add((char) commonItem);
        }
        return ruckSacks;
    }

    public int getSumPrio() {
        return characters
                .stream()
                .map(character -> (int) character.charValue())
                .map(integer -> {
                    if (integer > 96) {
                        return integer - 96;
                    } else {
                        return integer - 64 + 26;
                    }
                })
                .reduce(0, Integer::sum);
    }
}
