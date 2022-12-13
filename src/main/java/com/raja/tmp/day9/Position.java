package com.raja.tmp.day9;

import static java.lang.Math.abs;
import static java.lang.Math.max;

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

    public Position right() {
        return position(x + 1, y);
    }

    public Position left() {
        return position(x - 1, y);
    }

    public int distanceTo(Position headPosition) {
        return max(abs(headPosition.x - x), abs(headPosition.y - y));
    }

    public Position moveTo(Position headPosition) {
        if (up().up().atSamePositionAs(headPosition)) {
            return up();
        }
        if (down().down().atSamePositionAs(headPosition)) {
            return down();
        }
        if (right().right().atSamePositionAs(headPosition)) {
            return right();
        }
        if (left().left().atSamePositionAs(headPosition)) {
            return left();
        }

        if (up().right().isOneAwayFrom(headPosition)) {
            return up().right();
        }
        if (up().left().isOneAwayFrom(headPosition)) {
            return up().left();
        }
        if (down().right().isOneAwayFrom(headPosition)) {
            return down().right();
        }
        if (down().left().isOneAwayFrom(headPosition)) {
            return down().left();
        }

        return this;
    }

    private boolean atSamePositionAs(Position headPosition) {
        return distanceTo(headPosition) == 0;
    }

    private boolean isOneAwayFrom(Position headPosition) {
        return distanceTo(headPosition) == 1;
    }
}
