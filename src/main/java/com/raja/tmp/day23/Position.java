package com.raja.tmp.day23;

public record Position(int x, int y) {
    public static Position position(int x, int y) {
        return new Position(x, y);
    }

    public Position up() {
        return position(x, y - 1);
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

    public Position move(Direction direction) {
        return direction.move(this);
    }
}
