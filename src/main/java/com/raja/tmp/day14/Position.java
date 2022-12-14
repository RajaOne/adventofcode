package com.raja.tmp.day14;

public record Position(int x, int y) {

    public static Position position(int x, int y) {
        return new Position(x, y);
    }

    public Position down() {
        return position(x, y + 1);
    }

    public Position left() {
        return position(x - 1, y);
    }

    public Position right() {
        return position(x + 1, y);
    }
}
