package com.raja.tmp.day9;

public enum Direction {
    UP {
        @Override
        public Position move(Position position) {
            return position.up();
        }
    },
    DOWN {
        @Override
        public Position move(Position position) {
            return position.down();
        }
    },
    RIGHT {
        @Override
        public Position move(Position position) {
            return position.right();
        }
    },
    LEFT {
        @Override
        public Position move(Position position) {
            return position.left();
        }
    };

    public static Direction directionFrom(String input) {
        return switch (input) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "R" -> RIGHT;
            case "L" -> LEFT;
            default -> throw new RuntimeException("no direciton");
        };
    }

    public abstract Position move(Position headPosition);
}
