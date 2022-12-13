package com.raja.tmp.day11;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

@Getter
@Setter
public class Monkey {
    private Queue<MyNumber> items;
    private BiFunction<MyNumber, List<Integer>, MyNumber> operation;
    private BiPredicate<Integer, MyNumber> test;
    private BiPredicate<Integer, MyNumber> testSimple;
    private BiConsumer<MyNumber, MonkeyMiddle> ifFalse;
    private BiConsumer<MyNumber, MonkeyMiddle> ifTrue;
    private int operated = 0;
    private int mod;
    private List<Integer> mods;

    public Monkey() {
        items = new LinkedList<>();
    }

    public static Monkey monkey() {
        return new Monkey();
    }

    public void inc() {
        operated++;
    }
}
