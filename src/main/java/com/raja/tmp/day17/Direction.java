package com.raja.tmp.day17;

public enum Direction {
    LEFT,
    RIGHT;


    public static Direction directionFromString(String in) {
        return switch (in) {
            case ">" -> RIGHT;
            case "<" -> LEFT;
            default -> throw new RuntimeException("cannot read '" + in + "'");
        };
    }

}
