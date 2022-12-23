package com.raja.tmp.day18;

public record Point(int x, int y, int z) {

    public Point down() {
        return new Point(x, y, z - 1);
    }

    public Point up() {
        return new Point(x, y, z + 1);
    }

    public Point north() {
        return new Point(x, y+1, z);
    }

    public Point south() {
        return new Point(x, y-1, z);
    }

    public Point left() {
        return new Point(x-1, y, z);
    }

    public Point right() {
        return new Point(x + 1, y, z);
    }
}
