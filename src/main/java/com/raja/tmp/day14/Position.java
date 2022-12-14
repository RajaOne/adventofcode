package com.raja.tmp.day14;

import static java.lang.Integer.parseInt;

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

    public static Position positionFromString(String coord) {
        String[] xyCoord = coord.split(",");
        int x = parseInt(xyCoord[0]);
        int y = parseInt(xyCoord[1]);
        return position(x, y);
    }
}
