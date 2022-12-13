package com.raja.tmp.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.raja.tmp.day1.Elf.elf;
import static java.lang.Integer.parseInt;

public class CaloricCounter {

    List<Elf> elfList;

    private CaloricCounter(String input) {
        elfList = new ArrayList<>();

        String[] split = input.split("\n");
        Elf elf = elf();
        for (String calorie : split) {
            if (calorie.isEmpty()) {
                elfList.add(elf);
                elf = elf();
                continue;
            }
            int parseInt = parseInt(calorie);
            elf = elf.add(parseInt);
        }
        elfList.add(elf);
    }

    public static CaloricCounter caloricCounter(String input) {
        return new CaloricCounter(input);
    }

    public int getMax() {
        return elfList.stream()
                .map(Elf::total)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public int getTopThree() {
        return elfList.stream()
                .map(Elf::total)
                .sorted(Collections.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);
    }
}
