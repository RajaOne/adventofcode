package com.raja.tmp.day17;

public record Position(int x, long y) {

    public static Position position(int x, long y) {
        return new Position(x, y);
    }

    public Position copy() {
        return position(x, y);
    }

    public Position moveRight(int toMoveToRight) {
        return position(x + toMoveToRight, y);
    }

    public Position moveUp(long toMoveUp) {
        return position(x, y + toMoveUp);
    }

    public Position left() {
        return position(x - 1, y);
    }

    public Position right() {
        return position(x + 1, y);
    }

    public Position down() {
        return position(x, y - 1);
    }
}
