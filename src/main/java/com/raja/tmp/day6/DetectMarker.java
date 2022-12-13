package com.raja.tmp.day6;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DetectMarker {

    List<Character> characters;

    public DetectMarker() {
        characters = new ArrayList<>();
    }

    public static DetectMarker detectMarker(String input) {
        DetectMarker detectMarker = new DetectMarker();
        List<Character> characters = input.chars()
                .mapToObj(value -> (char) value)
                .toList();

        detectMarker.getCharacters().addAll(characters);
        return detectMarker;
    }

    public int firstMarker() {
        for (int i = 0; i < characters.size() - 4; i++) {
            List<Character> characters1 = characters.subList(i, i + 4);
            long distinctCount = characters1.stream()
                    .distinct()
                    .count();
            if (distinctCount != 4) {
                continue;
            }
            return i + 4;
        }

        return 0;
    }

    public int firstMessageMarker() {
        for (int i = 0; i < characters.size() - 14; i++) {
            List<Character> characters1 = characters.subList(i, i + 14);
            long distinctCount = characters1.stream()
                    .distinct()
                    .count();
            if (distinctCount != 14) {
                continue;
            }
            return i + 14;
        }

        return 0;
    }
}
