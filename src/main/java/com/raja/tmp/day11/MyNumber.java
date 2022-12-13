package com.raja.tmp.day11;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MyNumber {

    int begin;
    List<Integer> numbers;

    public MyNumber(int begin, int monkeys) {
        this.begin = begin;
        numbers = new ArrayList<>();
        for (int i = 0; i < monkeys; i++) {
            numbers.add(begin);
        }
    }

    public MyNumber add(MyNumber addition, List<Integer> mods) {
        begin += addition.getBegin();
        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            Integer numberToAdd = addition.getNumbers().get(i);
            Integer add = (number + numberToAdd) % mods.get(i);
            numbers.set(i, add);
        }
        return this;
    }

    public MyNumber multiply(MyNumber mult, List<Integer> mods) {
        begin *= mult.getBegin();
        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            Integer numberToMult = mult.getNumbers().get(i);
            int newNumber = (number * numberToMult) % mods.get(i);
            numbers.set(i, newNumber);
        }
        return this;
    }

    public boolean isDivisibleBy(int index) {
        return numbers.get(index) == 0;
    }

    public boolean isDivisibleBySimple(int mod) {
        return begin % mod == 0;
    }

    public void divideBy(int i) {
        begin = begin / i;
    }
}
