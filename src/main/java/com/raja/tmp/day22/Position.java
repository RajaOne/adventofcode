package com.raja.tmp.day22;

public record Position(int x, int y) {

    public static Position aPosition(int x, int y) {
        return new Position(x, y);
    }

    public Position move(Direction currentDirection) {
        return currentDirection.move(this);
    }

    public Position up() {
        return aPosition(x, y - 1);
    }

    public Position down() {
        return aPosition(x, y + 1);
    }

    public Position left() {
        return aPosition(x - 1, y);
    }

    public Position right() {
        return aPosition(x + 1, y);
    }
}
