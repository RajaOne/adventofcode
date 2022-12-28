package com.raja.tmp.day22;

public enum Direction {
    UP(3) {
        @Override
        public Direction turnLeft() {
            return LEFT;
        }

        @Override
        public Direction turnRight() {
            return RIGHT;
        }

        @Override
        public Position move(Position position) {
            return position.up();
        }
    },
    RIGHT(0) {
        @Override
        public Direction turnLeft() {
            return UP;
        }

        @Override
        public Direction turnRight() {
            return DOWN;
        }

        @Override
        public Position move(Position position) {
            return position.right();
        }
    },
    DOWN(1) {
        @Override
        public Direction turnLeft() {
            return RIGHT;
        }

        @Override
        public Direction turnRight() {
            return LEFT;
        }

        @Override
        public Position move(Position position) {
            return position.down();
        }
    },
    LEFT(2) {
        @Override
        public Direction turnLeft() {
            return DOWN;
        }

        @Override
        public Direction turnRight() {
            return UP;
        }

        @Override
        public Position move(Position position) {
            return position.left();
        }
    };

    public final int value;
    Direction(int value) {
        this.value = value;
    }

    public abstract Direction turnLeft();

    public abstract Direction turnRight();

    public abstract Position move(Position position);
}
