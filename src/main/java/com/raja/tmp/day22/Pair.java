package com.raja.tmp.day22;

public record Pair(Position position, Direction direction) {

    public static Pair pair(Position position, Direction direction) {
        return new Pair(position, direction);
    }
}
